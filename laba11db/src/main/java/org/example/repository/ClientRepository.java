package org.example.repository;

import org.example.entity.Client;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends Neo4jRepository<Client, String> {
}