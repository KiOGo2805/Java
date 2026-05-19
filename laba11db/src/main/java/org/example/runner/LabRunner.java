package org.example.runner;

import org.example.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LabRunner implements CommandLineRunner {

    private final ClientRepository clientRepo;
    private final Neo4jTemplate neo4jTemplate;

    public LabRunner(ClientRepository clientRepo, Neo4jTemplate neo4jTemplate) {
        this.clientRepo = clientRepo;
        this.neo4jTemplate = neo4jTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n=================================================");
        System.out.println("УСПІШНО ПІДКЛЮЧЕНО ДО NEO4J AURA ЧЕРЕЗ SPRING BOOT!");
        System.out.println("=================================================\n");

        System.out.println("=== 1. РОБОТА ЧЕРЕЗ Neo4jRepository ===");
        System.out.println("Загальна кількість клієнтів у базі: " + clientRepo.count());
        clientRepo.findAll().forEach(client ->
                System.out.println(" - " + client.getName() + " (Місто: " + client.getCity() + ", Тел: " + client.getPhone() + ")")
        );

        System.out.println("\n=== 2. РОБОТА ЧЕРЕЗ Neo4jTemplate (Кастомні 5 запитів) ===");

        System.out.println("\nЗапит 1: Клієнти агента Іванченка:");
        String q1 = "MATCH (c:Client)-[:SIGNED]->(con:Contract)-[:MANAGED_BY]->(a:Agent {name: 'Іванченко Іван'}) RETURN c.name AS ClientName, con.number AS Contract";
        neo4jTemplate.findAll(q1, Map.class).forEach(row -> System.out.println("   " + row));

        System.out.println("\nЗапит 2: Послуги, які придбав Степанченко Степан:");
        String q2 = "MATCH (c:Client {name: 'Степанченко Степан'})-[:SIGNED]->(:Contract)-[:INCLUDES]->(s:Service) RETURN s.title AS ServicePurchased";
        neo4jTemplate.findAll(q2, Map.class).forEach(row -> System.out.println("   " + row));

        System.out.println("\nЗапит 3: Загальна сума премій по кожному агенту:");
        String q3 = "MATCH (a:Agent)<-[:MANAGED_BY]-(con:Contract) RETURN a.name AS AgentName, SUM(con.premium) AS TotalPremiums";
        neo4jTemplate.findAll(q3, Map.class).forEach(row -> System.out.println("   " + row));

        System.out.println("\nЗапит 4: Контракти з премією більше 20 000:");
        String q4 = "MATCH (con:Contract) WHERE con.premium > 20000 RETURN con.number AS Number, con.premium AS Premium";
        neo4jTemplate.findAll(q4, Map.class).forEach(row -> System.out.println("   " + row));

        System.out.println("\nЗапит 5: Ланцюжок для контракту CN-002:");
        String q5 = "MATCH (c:Client)-[:SIGNED]->(con:Contract {number: 'CN-002'})-[:MANAGED_BY]->(a:Agent) RETURN c.name AS Client, a.name AS Agent";
        neo4jTemplate.findAll(q5, Map.class).forEach(row -> System.out.println("   " + row));

        System.out.println("\n=================================================");
        System.exit(0);
    }
}