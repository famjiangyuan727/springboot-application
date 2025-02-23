package com.example.myapp.dao;

import com.example.myapp.model.UserInfoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfoModel, Long> {
    Page<UserInfoModel> findAll(Pageable pageable);
    Page<UserInfoModel> findByName(String name, Pageable pageable);
    boolean existsByEmail(String email);
}
