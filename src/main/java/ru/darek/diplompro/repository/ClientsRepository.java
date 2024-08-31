package ru.darek.diplompro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.darek.diplompro.entities.Client;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
}
