package com.sim.springboot.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sim.springboot.models.Article;
import com.sim.springboot.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
}
