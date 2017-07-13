package com.talan.rsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talan.rsa.entity.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long>{

}
