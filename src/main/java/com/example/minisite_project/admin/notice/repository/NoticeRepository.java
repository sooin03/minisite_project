package com.example.minisite_project.admin.notice.repository;

import com.example.minisite_project.admin.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    //Optional<List<Notice>> findByCategoryId(long categoryId);
    
}
