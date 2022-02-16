package com.sim.springboot.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sim.springboot.models.Article;
import com.sim.springboot.models.Category;
import com.sim.springboot.repos.CategoryRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")

public class CategoryController {
	
	@Autowired
	CategoryRepository catRepo;
	
	@GetMapping("/categories")
	  public ResponseEntity<List<Category>> getAllCategories() {
	    List<Category> categories = new ArrayList<Category>();
	    categories = catRepo.findAll();
	    if(categories.isEmpty()) {
	    	 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(categories, HttpStatus.OK);
	    
	    
	  }
	
	@GetMapping("/categories/{id}")
	  public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
	    Category cat = catRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Category with id = " + id + " not found !"));
	    return new ResponseEntity<>(cat, HttpStatus.OK);
	  }
	
	@PostMapping("/categories")
	  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		System.out.println("entering the new categorie method");
	    Category _category = catRepo.save(new Category(category.getTitle()));
	    return new ResponseEntity<>(_category, HttpStatus.CREATED);
	  }
	
	 @PutMapping("/categories/{id}")
	  public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
	    Category _category = catRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Category with id = " + id + " not found !"));
	    _category.setTitle(category.getTitle());
	    return new ResponseEntity<>(catRepo.save(_category), HttpStatus.OK);
	  }
	
	 @DeleteMapping("/categories/{id}")
	  public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") int id) {
	    catRepo.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	 
	 
	 @GetMapping("/categories/{id}/articles")
	  public ResponseEntity<List<Article>> getCategoryArticles(@PathVariable("id") int id) {
	    List<Article> articles = new ArrayList<Article>();
	    Category cat = catRepo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Category with id = " + id + " not found !"));
	    articles = cat.getArticles();
	    if(articles.isEmpty()) {
	    	 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(articles, HttpStatus.OK);
	    
	    
	  }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
