package com.devcraft.springrestwebflux.repository;

import com.devcraft.springrestwebflux.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {

}
