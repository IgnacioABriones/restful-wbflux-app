package com.mitocode.controller;

import com.mitocode.model.Dish;
import com.mitocode.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private IDishService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<Dish>>> findAll(){
        Flux<Dish> fx = service.findAll();
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON) //a veces por norma
                .body(fx)).defaultIfEmpty(ResponseEntity.noContent().build());
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<Dish>> findById(@PathVariable("id")String id){
        //Mono<Dish>
        return service.findById(id)
                .map(e-> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Dish>> save(@RequestBody Dish dish, final ServerHttpRequest req){
        return service.save(dish) //Mono<Dish>
                .map(e-> ResponseEntity
                        .created(URI.create(req.getURI().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Dish>> update(@PathVariable("id") String id, @RequestBody Dish dish) {
        dish.setId(id);
        Mono<Dish> monoBody = Mono.just(dish);
        Mono<Dish> monoDB = service.findById(id);

        return monoDB.zipWith(monoBody, (db, d) -> {
            db.setId(id);
            db.setName(d.getName());
            db.setPrice(d.getPrice());
            db.setStatus(d.getStatus());
            return db;
        })
                .flatMap(service::update) //e -> service.update(e)
                .map(e->ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id")String id){
        return service.findById(id)
                .flatMap(e -> service.delete(e.getId())
                       // .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
                        .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
