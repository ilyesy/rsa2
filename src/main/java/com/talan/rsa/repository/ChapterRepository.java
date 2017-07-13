package com.talan.rsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talan.rsa.entity.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

}
