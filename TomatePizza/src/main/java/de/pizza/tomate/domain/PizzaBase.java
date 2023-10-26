package de.pizza.tomate.domain;

import liquibase.pro.packaged.B;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PizzaBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pizza_base_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pizza_type_id")
    private PizzaType pizzaType;

    @ManyToOne
    @JoinColumn(name = "pizza_size_id")
    private PizzaSize pizzaSize;

    private Double price;

    private Boolean ordered;

    private Boolean deleted ;
}
