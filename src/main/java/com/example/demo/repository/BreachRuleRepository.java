package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BreachRule;
import java.util.Optional;

public interface BreachRuleRepository extends JpaRepository<BreachRule, Long> {
    Optional<BreachRule> findByIsDefaultRuleTrue();
}
