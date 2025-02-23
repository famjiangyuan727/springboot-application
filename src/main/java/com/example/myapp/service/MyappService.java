package com.example.myapp.service;

import com.example.myapp.dto.UserInfoDto;
import com.example.myapp.model.UserInfoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MyappService {
    List<UserInfoDto> getAllUsers();
    Page<UserInfoDto> getAllUsers(Pageable pageable);
    Page<UserInfoDto> getUsersByName(String name, Pageable pageable);
    UserInfoDto getUserById(Long id);
    UserInfoDto createUser(UserInfoDto user);
    UserInfoDto updateUser(Long id, UserInfoDto userDetails);
}
