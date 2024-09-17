package com.jntcruz.Desafio.anotai.service;

import com.jntcruz.Desafio.anotai.domain.category.exceptions.CategoryNotFoundException;
import com.jntcruz.Desafio.anotai.domain.product.Product;
import com.jntcruz.Desafio.anotai.domain.product.ProductDTO;
import com.jntcruz.Desafio.anotai.domain.product.exceptions.ProductNotFoundException;
import com.jntcruz.Desafio.anotai.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryService categoryService;
    private final ProductRepository repository;


    public Product insert(ProductDTO productData){

        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);
        Product newProduct = new Product(productData);

        this.repository.save(newProduct);


        return newProduct;
    }

    public Product update(String id, ProductDTO productData){
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        if(!productData.title().isEmpty()) product.setTitle(productData.title());
        if(!productData.description().isEmpty()) product.setDescription(productData.description());
        if(!(productData.price() == null)) product.setPrice(productData.price());
        if(!(productData.categoryId() == null)) product.setCategory(productData.categoryId());

        this.repository.save(product);


        return product;
    }

    public void delete(String id){
        Product product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.repository.delete(product);
    }

    public List<Product> getAll(){
        return this.repository.findAll();
    }
}
