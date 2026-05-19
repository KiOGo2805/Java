package org.example;

import redis.clients.jedis.Jedis;
import java.util.Map;
import java.util.Set;

public class Main {
    static void main() {
        try (Jedis jedis = new Jedis("localhost", 6379)) {

            System.out.println("Статус підключення: " + jedis.ping() + " (Успішно!)\n");
            System.out.println("=== Демонстрація CRUD операцій для колекції Clients ===\n");

            String clientId = "3";
            String clientKey = "client:" + clientId;

            System.out.println("1. [CREATE] Додаємо нового клієнта...");
            jedis.hset(clientKey, "name", "Василенко Василь");
            jedis.hset(clientKey, "phone", "+380501112233");
            jedis.hset(clientKey, "address", "Одеса");
            jedis.sadd("clients", clientId);
            System.out.println("   Клієнта створено!\n");

            System.out.println("2. [READ] Читаємо дані з бази...");
            Map<String, String> clientData = jedis.hgetAll(clientKey);
            System.out.println("   Дані клієнта: " + clientData);

            Set<String> allClients = jedis.smembers("clients");
            System.out.println("   Список ID всіх клієнтів у базі: " + allClients + "\n");

            System.out.println("3. [UPDATE] Оновлюємо номер телефону клієнта...");
            jedis.hset(clientKey, "phone", "+380999999999");
            System.out.println("   Новий телефон: " + jedis.hget(clientKey, "phone") + "\n");

            System.out.println("4. [DELETE] Видаляємо клієнта з бази...");
            jedis.del(clientKey);
            jedis.srem("clients", clientId);

            Map<String, String> deletedData = jedis.hgetAll(clientKey);
            System.out.println("   Спроба прочитати після видалення: " +
                    (deletedData.isEmpty() ? "Дані відсутні (успішно видалено)" : deletedData));

        } catch (Exception e) {
            System.err.println("Помилка підключення до Redis: " + e.getMessage());
        }
    }
}