package com.jntcruz.Desafio.anotai.repositories;

import com.jntcruz.Desafio.anotai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
