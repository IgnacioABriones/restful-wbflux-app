package com.mitocode.controller;

import com.mitocode.model.Dish;
import com.mitocode.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private IDishService service;

    @GetMapping
    public Flux<Dish> findAll(){
        return service.findAll();
    }


    @GetMapping("/{id}")
    public Mono<Dish> findById(@PathVariable("id")String id){
        return service.findById(id);
    }

    @PostMapping
    public Mono<Dish> save(@RequestBody Dish dish){
        return service.save(dish);
    }

    @PutMapping
    public Mono<Dish> update(@RequestBody Dish dish){
        return service.update(dish);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id")String id){
        return service.delete(id);
    }

}
