package org.example;

public class Lab5Main {
    public static void main(String[] args) {
        try {
            // Ініціалізуємо наші DAO
            UserDao userDao = new UserDao();
            CategoryDao categoryDao = new CategoryDao();
            ComponentDao componentDao = new ComponentDao();

            System.out.println("--- 1. Тест Users DAO ---");
            userDao.register("admin", "12345");
            User user = userDao.login("admin", "12345");
            if (user != null) System.out.println("✅ Логін успішний!");
            userDao.updatePassword(1, "new_password");

            System.out.println("\n--- 2. Тест Categories DAO ---");
            categoryDao.create("Процесори");
            categoryDao.create("Відеокарти");
            categoryDao.update(1, "CPU");
            categoryDao.printAll();

            System.out.println("\n--- 3. Тест Components DAO ---");
            componentDao.create("Intel Core i5", 200.0, 1);
            componentDao.create("RTX 4070", 600.0, 2);
            componentDao.updatePrice(1, 180.0);
            componentDao.searchByName("RTX");
            componentDao.printMetadata();

            System.out.println("\n--- 4. Очищення бази (Teardown) ---");
            componentDao.delete(1);
            componentDao.delete(2);
            categoryDao.delete(1);
            categoryDao.delete(2);
            userDao.delete(1);

            // Скидаємо лічильники ID (щоб можна було запускати багато разів)
            Database.getConnection().createStatement().executeUpdate("ALTER TABLE components AUTO_INCREMENT = 1");
            Database.getConnection().createStatement().executeUpdate("ALTER TABLE categories AUTO_INCREMENT = 1");
            Database.getConnection().createStatement().executeUpdate("ALTER TABLE users AUTO_INCREMENT = 1");
            System.out.println("🧹 Базу очищено!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}