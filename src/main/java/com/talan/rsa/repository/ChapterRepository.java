package com.talan.rsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.talan.rsa.entity.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
