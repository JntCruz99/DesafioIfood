package com.jntcruz.Desafio.anotai.service;

import com.jntcruz.Desafio.anotai.domain.category.Category;
import com.jntcruz.Desafio.anotai.domain.category.CategoryDTO;
import com.jntcruz.Desafio.anotai.domain.category.exceptions.CategoryNotFoundException;
import com.jntcruz.Desafio.anotai.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;


    public Category insert(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);

        this.repository.save(newCategory);


        return newCategory;
    }

    public Category update(String id, CategoryDTO categoryData){
        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if(!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if(!categoryData.description().isEmpty()) category.setDescription(categoryData.description());


        this.repository.save(category);

        return category;
    }

    public void delete(String id){
        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);
    }

    public List<Category> getAll(){
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id){
        return this.repository.findById(id);
    }
}
