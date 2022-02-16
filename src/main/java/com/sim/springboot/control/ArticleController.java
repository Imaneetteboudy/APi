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

import com.sim.springboot.models.Category;
import com.sim.springboot.models.Article;
import com.sim.springboot.repos.ArticleRepository;
import com.sim.springboot.repos.CategoryRepository;

@CrossOrigin
@RestController
@RequestMapping("/api")

public class ArticleController {
	
	@Autowired
	ArticleRepository artRepo;
	
	@Autowired
	CategoryRepository catRepo;
	
	@GetMapping("/articles")
	  public ResponseEntity<List<Article>> getAllArticles() {
	    List<Article> articles = new ArrayList<Article>();
	    articles = artRepo.findAll();
	    if(articles.isEmpty()) {
	    	 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    
	    return new ResponseEntity<>(articles, HttpStatus.OK);
	    
	    
	  }
	
	@GetMapping("/articles/{id}")
	  public ResponseEntity<Article> getArticleById(@PathVariable("id") int id) {
	    Article art = artRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Article with id = " + id + " not found !"));
	    return new ResponseEntity<>(art, HttpStatus.OK);
	  }
	
	@PostMapping("/articles")
	  public ResponseEntity<Article> createArticle(@RequestBody Article article) {
	    Article _article = artRepo.save(new Article(article.getLabel(), article.getPrice()));
	    return new ResponseEntity<>(_article, HttpStatus.CREATED);
	  }
	
	 @PutMapping("/articles/{id}")
	  public ResponseEntity<Article> updateArticle(@PathVariable("id") int id, @RequestBody Article article) {
	    Article _article = artRepo.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Article with id = " + id + " not found !"));
	    _article.setLabel(article.getLabel());
	    _article.setPrice(article.getPrice());
	    return new ResponseEntity<>(artRepo.save(_article), HttpStatus.OK);
	  }
	
	 @DeleteMapping("/articles/{id}")
	  public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") int id) {
	    artRepo.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }
	 
	 @GetMapping("/articles/{id}/category")
	  public ResponseEntity<Category> getArticleCategory(@PathVariable("id") int id) {
	    
	    Article article = artRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article with id = " + id + " not found !"));
	    Category category = article.getCategory();
	    
	    return new ResponseEntity<>(category, HttpStatus.OK);
	    
	    
	  }
	 
	 @PutMapping("/articles/{id}/category")
	  public ResponseEntity<Article> updateArticle(@PathVariable("id") int id, @RequestBody Category category) {
	    Article _article = artRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article with id = " + id + " not found !"));
	    _article.setCategory(category);
	    return new ResponseEntity<>(artRepo.save(_article), HttpStatus.OK);
	  }
	
}
