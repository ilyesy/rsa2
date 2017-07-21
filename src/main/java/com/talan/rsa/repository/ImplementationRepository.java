package com.talan.rsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.talan.rsa.entity.Implementation;

@Repository
public interface ImplementationRepository extends JpaRepository<Implementation, Long>{
	
	@Query(value = "select i from implementations i where i.rule.id = ?1")
	public List<Implementation> findImpsByRule(long rule);
}
