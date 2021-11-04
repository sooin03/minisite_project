package com.example.minisite_project.webtoon.repository;

import com.example.minisite_project.webtoon.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

    Optional<List<Webtoon>> findByCategoryId(long categoryId);
    
}
