package de.pizza.tomate.repository;

import de.pizza.tomate.domain.Order;
import de.pizza.tomate.domain.OrderPizza;
import de.pizza.tomate.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface OrderPizzaRepository extends JpaRepository<OrderPizza,Integer> {
    OrderPizza findByPizza(Pizza pizza);

    OrderPizza findByOrderAndPizza(Order order, Pizza pizza);

    List<OrderPizza> findByOrder(Order order);

    @Modifying
    int deleteByOrder(Order order);
}
