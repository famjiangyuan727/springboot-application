package com.example.myapp.service.impl;

import com.example.myapp.dao.UserInfoRepo;
import com.example.myapp.dto.UserInfoDto;
import com.example.myapp.exception.ResourceNotFoundException;
import com.example.myapp.model.UserInfoModel;
import com.example.myapp.service.MyappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyappServiceImpl implements MyappService {

    @Autowired
    UserInfoRepo userInfoRepo;

    @Override
    public List<UserInfoDto> getAllUsers() {
        return userInfoRepo.findAll().stream()
                .map(user -> new UserInfoDto(user))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserInfoDto> getAllUsers(Pageable pageable) {
        return userInfoRepo.findAll(pageable)
                .map(user -> new UserInfoDto(user));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserInfoDto> getUsersByName(String name, Pageable pageable) {
        return userInfoRepo.findByName(name,pageable)
                .map(user -> new UserInfoDto(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoDto getUserById(Long id) {
        UserInfoModel user = userInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        return new UserInfoDto(user);
    }

    @Override
    @Transactional
    @Retryable(value = { DataAccessException.class, TransactionException.class }, backoff = @Backoff(delay = 1000, multiplier = 2))
    public UserInfoDto createUser(UserInfoDto user) {
        boolean existed = userInfoRepo.existsByEmail(user.getEmail());
        if (existed) {
            throw new RuntimeException("Email already existed");
        }
        UserInfoModel userInfo = new UserInfoModel(user.getName(), user.getEmail(), user.getMobileNum());
        UserInfoModel savedUserInfo = userInfoRepo.save(userInfo);
        return new UserInfoDto(savedUserInfo);
    }

    @Override
    @Transactional
    @Retryable(value = { DataAccessException.class, TransactionException.class }, backoff = @Backoff(delay = 1000, multiplier = 2))
    public UserInfoDto updateUser(Long id, UserInfoDto user) {
        UserInfoModel userInfo = userInfoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
        boolean existed = userInfoRepo.existsByEmail(user.getEmail());
        if (existed) {
            throw new RuntimeException("Email already existed");
        }
        userInfo.setName(user.getName());
        userInfo.setEmail(user.getEmail());
        userInfo.setMobileNum(user.getMobileNum());
        UserInfoModel updatedUserInfo = userInfoRepo.save(userInfo);
        return new UserInfoDto(updatedUserInfo);
    }
}
