package com.devcraft.springrestwebflux.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.devcraft.springrestwebflux.domain.Category;
import com.devcraft.springrestwebflux.domain.Vendor;
import com.devcraft.springrestwebflux.repository.CategoryRepository;
import com.devcraft.springrestwebflux.repository.VendorRepository;
import reactor.core.publisher.Flux;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) {
        loadCategories();
        loadVendors();
    }

    private void loadCategories() {
        // make five unique categories variables
        Category fruits = Category.builder().description("Fruits").build();
        Category dried = Category.builder().description("Dried").build();
        Category fresh = Category.builder().description("Fresh").build();
        Category exotic = Category.builder().description("Exotic").build();
        Category nuts = Category.builder().description("Nuts").build();

        // save the categories to the database
        categoryRepository.deleteAll()
                .thenMany(
                        Flux.just(fruits, dried, fresh, exotic, nuts)
                        .flatMap(categoryRepository::save))
                .then(categoryRepository.count())
                .subscribe(count -> System.out.println("Loaded Categories: " + count));
    }

    private void loadVendors() {
        Vendor vendor1 = Vendor.builder().firstName("Joe").lastName("Buck").build();
        Vendor vendor2 = Vendor.builder().firstName("Michael").lastName("Weston").build();
        Vendor vendor3 = Vendor.builder().firstName("Jessie").lastName("Waters").build();
        Vendor vendor4 = Vendor.builder().firstName("Bill").lastName("Nershi").build();
        Vendor vendor5 = Vendor.builder().firstName("Jimmy").lastName("Buffet").build();

        vendorRepository.deleteAll()
                .thenMany(Flux.just(vendor1, vendor2, vendor3, vendor4, vendor5)
                        .flatMap(vendorRepository::save))
                .then(vendorRepository.count())
                .subscribe(count -> System.out.println("Loaded Vendors: " + count));
    }
}
