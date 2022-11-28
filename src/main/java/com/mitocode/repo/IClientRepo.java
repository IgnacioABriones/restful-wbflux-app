package com.mitocode.repo;

import com.mitocode.model.Client;
import com.mitocode.model.Dish;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IClientRepo extends ReactiveMongoRepository<Client, String> {
}
