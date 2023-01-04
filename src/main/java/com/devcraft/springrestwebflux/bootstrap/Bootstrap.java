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
        Category fruits = new Category();
        fruits.setDescription("Fruits");

        Category dried = new Category();
        dried.setDescription("Dried");

        Category fresh = new Category();
        fresh.setDescription("Fresh");

        Category exotic = new Category();
        exotic.setDescription("Exotic");

        Category nuts = new Category();
        nuts.setDescription("Nuts");

        // save the categories to the database
        categoryRepository.deleteAll()
                .thenMany(
                        Flux.just(fruits, dried, fresh, exotic, nuts)
                        .flatMap(categoryRepository::save))
                .then(categoryRepository.count())
                .subscribe(count -> System.out.println("Loaded Categories: " + count));
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setFirstName("Joe");
        vendor1.setLastName("Buck");

        Vendor vendor2 = new Vendor();
        vendor2.setFirstName("Michael");
        vendor2.setLastName("Weston");

        Vendor vendor3 = new Vendor();
        vendor3.setFirstName("Jessie");
        vendor3.setLastName("Waters");

        Vendor vendor4 = new Vendor();
        vendor4.setFirstName("Bill");
        vendor4.setLastName("Nershi");

        Vendor vendor5 = new Vendor();
        vendor5.setFirstName("Jimmy");
        vendor5.setLastName("Buffet");

        vendorRepository.deleteAll()
                .thenMany(Flux.just(vendor1, vendor2, vendor3, vendor4, vendor5)
                        .flatMap(vendorRepository::save))
                .then(vendorRepository.count())
                .subscribe(count -> System.out.println("Loaded Vendors: " + count));
    }
}
