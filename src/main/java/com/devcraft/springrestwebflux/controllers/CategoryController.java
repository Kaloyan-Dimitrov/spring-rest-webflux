package com.devcraft.springrestwebflux.controllers;

import com.devcraft.springrestwebflux.domain.Category;
import com.devcraft.springrestwebflux.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    public static final String BASE_URL = "/api/v1/categories/";

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Flux<Category> list() {
        return categoryRepository.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    Mono<Category> getById(@PathVariable String id) {
        return categoryRepository.findById(id);
    }
}
