package de.pizza.tomate.controller;

import de.pizza.tomate.controller.dto.IngredientDTO;
import de.pizza.tomate.controller.dto.PizzaSizeDTO;
import de.pizza.tomate.domain.Ingredient;
import de.pizza.tomate.service.IngredientService;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/all")
    public List<IngredientDTO> findAll() {
        return ingredientService.findAll();
    }

    @PostMapping("/add")
    public IngredientDTO add(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.add(ingredientDTO);
    }

    @PutMapping("/update")
    public IngredientDTO update(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.update(ingredientDTO);
    }

    @DeleteMapping("/delete/{id}")
    public IngredientDTO delete(@PathVariable Integer id) {
        return ingredientService.delete(id);
    }
}