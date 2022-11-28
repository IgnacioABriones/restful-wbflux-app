package com.mitocode.service;

import com.mitocode.model.Dish;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDishService extends ICRUD<Dish, String>  {

   // Flux<Dish> dishMostExpensive();
}


