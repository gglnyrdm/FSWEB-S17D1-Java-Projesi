package com.workintech.animal;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    private Map<Integer,Animal> animals;
    @PostConstruct
    public void init(){
        animals=new HashMap<>();
    }
    public AnimalController(Map<Integer, Animal> animals) {
        System.out.println("Animal created");
    }
    @GetMapping("/")
    public List<Animal> getAll(){
        return animals.values().stream().toList();
    }
    @GetMapping("/{id}")
    public Animal getById(@PathVariable int id){
        return animals.get(id);
    }
    @PostMapping("/")
    public Animal create(@RequestBody Animal animal){
        animals.put(animal.getId(), animal);
        return animals.get(animal.getId());
    }
    @PutMapping("/{id}")
    public Animal update(@PathVariable int id, @RequestBody Animal animal){
        animals.put(id,new Animal(id,animal.getName()));
        return animals.get(id);
    }
    @DeleteMapping("/{id}")
    public Animal delete(@PathVariable int id){
        return animals.remove(id);
    }
}
