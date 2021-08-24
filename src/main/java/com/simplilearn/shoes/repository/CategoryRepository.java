package com.simplilearn.shoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplilearn.shoes.model.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
