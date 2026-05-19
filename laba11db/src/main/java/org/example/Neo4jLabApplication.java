package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class Neo4jLabApplication {
    public static void main(String[] args) {
        SpringApplication.run(Neo4jLabApplication.class, args);
    }
}