package com.devcraft.springrestwebflux.repository;

import com.devcraft.springrestwebflux.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {

}
