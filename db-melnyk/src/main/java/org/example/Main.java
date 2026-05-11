package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("melnyk");
            MongoCollection<Document> contracts = database.getCollection("contracts");

            System.out.println("Успішно підключено! Виконуємо 5 агрегацій з Лабораторної №8...\n");

            System.out.println("=== 1. Топ способи оплат (Платежі >= 10000) ===");
            contracts.aggregate(Arrays.asList(
                    Document.parse("{ $unwind: '$payments' }"),
                    Document.parse("{ $match: { 'payments.amount': { $gte: 10000 } } }"),
                    Document.parse("{ $group: { _id: '$payments.payment_type', max_payment: { $max: '$payments.amount' }, total_payments: { $sum: 1 } } }"),
                    Document.parse("{ $sort: { max_payment: -1 } }"),
                    Document.parse("{ $project: { _id: 0, Спосіб_оплати: '$_id', Максимальний_платіж: '$max_payment', Кількість: '$total_payments' } }")
            )).forEach(doc -> System.out.println(doc.toJson()));

            System.out.println("\n=== 2. Статистика комісій агентів ===");
            contracts.aggregate(Arrays.asList(
                    Document.parse("{ $unwind: '$commissions' }"),
                    Document.parse("{ $match: { 'commissions.status': { $ne: 'Скасовано' } } }"),
                    Document.parse("{ $group: { _id: '$commissions.status', total_amount: { $sum: '$commissions.amount' }, avg_amount: { $avg: '$commissions.amount' } } }"),
                    Document.parse("{ $sort: { total_amount: -1 } }"),
                    Document.parse("{ $project: { _id: 0, Статус_комісії: '$_id', Загальна_сума: '$total_amount', Середня_сума: '$avg_amount' } }")
            )).forEach(doc -> System.out.println(doc.toJson()));

            System.out.println("\n=== 3. Топ агентів (Великі договори) ===");
            contracts.aggregate(Arrays.asList(
                    Document.parse("{ $match: { insurance_amount: { $gte: 500000 } } }"),
                    Document.parse("{ $group: { _id: '$agent.$id', total_premium: { $sum: '$premium' }, contract_count: { $sum: 1 } } }"),
                    Document.parse("{ $match: { total_premium: { $gt: 10000 } } }"),
                    Document.parse("{ $sort: { total_premium: -1 } }"),
                    Document.parse("{ $limit: 5 }")
            )).forEach(doc -> System.out.println(doc.toJson()));

            System.out.println("\n=== 4. Скільки всього сплатив кожен клієнт ===");
            contracts.aggregate(Arrays.asList(
                    Document.parse("{ $unwind: '$payments' }"),
                    Document.parse("{ $group: { _id: '$client.$id', total_paid: { $sum: '$payments.amount' } } }"),
                    Document.parse("{ $match: { total_paid: { $gt: 0 } } }"),
                    Document.parse("{ $sort: { total_paid: -1 } }"),
                    Document.parse("{ $project: { _id: 0, ID_Клієнта: '$_id', Всього_сплачено_грн: '$total_paid' } }")
            )).forEach(doc -> System.out.println(doc.toJson()));

            System.out.println("\n=== 5. Популярність страхових послуг ===");
            contracts.aggregate(Arrays.asList(
                    Document.parse("{ $match: { premium: { $gt: 0 } } }"),
                    Document.parse("{ $group: { _id: '$service.$id', total_insurance: { $sum: '$insurance_amount' }, num_contracts: { $sum: 1 } } }"),
                    Document.parse("{ $sort: { total_insurance: -1 } }"),
                    Document.parse("{ $skip: 0 }"),
                    Document.parse("{ $limit: 3 }")
            )).forEach(doc -> System.out.println(doc.toJson()));


            MongoCollection<Document> agents = database.getCollection("agents");
            MongoCollection<Document> clients = database.getCollection("clients");
            MongoCollection<Document> services = database.getCollection("services");

            System.out.println("=== 1. Селектор: Контракти з премією > 20000 ===");
            contracts.find(Document.parse("{ premium: { $gt: 20000 } }"))
                    .projection(Document.parse("{ client: 0, agent: 0, service: 0 }"))
                    .forEach(doc -> System.out.println(doc.toJson()));

            System.out.println("\n=== 2. Селектор: Агенти (комісія > 5 АБО ім'я 'Іван...') ===");
            agents.find(Document.parse("{ $or: [ { commission_rate: { $gt: 5 } }, { name: { $regex: '^Іван' } } ] }"))
                    .forEach(doc -> System.out.println(doc.toJson()));

            System.out.println("\n=== 3. Селектор: Клієнти (проєкція та сортування) ===");
            clients.find()
                    .projection(Document.parse("{ name: 1, phone: 1, _id: 0 }"))
                    .sort(Document.parse("{ name: -1 }"))
                    .forEach(doc -> System.out.println(doc.toJson()));

            System.out.println("\n=== 4. Селектор: Видалення даних ===");
            services.insertOne(Document.parse("{ _id: 99, title: 'Тимчасова послуга', description: 'Для видалення' }"));
            System.out.println("Тестову послугу з _id: 99 успішно додано.");
            services.deleteOne(Document.parse("{ _id: 99 }"));
            System.out.println("Тестову послугу з _id: 99 успішно видалено!");

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
}