package de.pizza.tomate.repository;

import de.pizza.tomate.domain.OrderPizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPizzaRepository extends JpaRepository<OrderPizza,Integer> {
}
