package com.devcraft.springrestwebflux.controllers;

import com.devcraft.springrestwebflux.domain.Category;
import com.devcraft.springrestwebflux.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
    public static final String ID = "someid";
    WebTestClient webTestClient;
    @Mock
    CategoryRepository categoryRepository;
    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    void list() {
        given(categoryRepository.findAll())
                .willReturn(Flux.just(Category.builder().description("Cat1").build(),
                                    Category.builder().description("Cat2").build()));

        webTestClient.get().uri(CategoryController.BASE_URL)
                .exchange()
                .expectBody(Category.class);
//                .hasSize(2);
    }

    @Test
    void getById() {
        given(categoryRepository.findById(ID))
                .willReturn(Mono.just(Category.builder().description("Cat1").build()));

        webTestClient.get().uri(CategoryController.BASE_URL + ID)
                .exchange()
                .expectBody(Category.class)
                .value(category -> assertEquals("Cat1", category.getDescription()));
    }
}