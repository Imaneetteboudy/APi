package com.sim.springboot.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sim.springboot.models.Article;
import com.sim.springboot.models.Category;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
