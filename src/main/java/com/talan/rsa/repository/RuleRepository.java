package com.talan.rsa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talan.rsa.entity.Rule;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long>{
	
	Page<Rule> findAllByTitle(Pageable p, String search);

}
