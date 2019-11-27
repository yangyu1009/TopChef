package com.topchef.demo.repository;

import com.topchef.demo.domain.TopChefRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopChefReciptRepository extends JpaRepository<TopChefRecipe, String>{
}
