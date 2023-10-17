package de.pizza.tomate.repository;

import de.pizza.tomate.domain.Order;
import liquibase.pro.packaged.O;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OderRepository extends JpaRepository<Order, Integer> {
}
