package com.talan.rsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talan.rsa.entity.Implementation;

@Repository
public interface ImplementationRepository extends JpaRepository<Implementation, Long>{
}
