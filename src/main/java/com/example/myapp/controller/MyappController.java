package com.example.myapp.controller;

import com.example.myapp.dto.UserInfoDto;
import com.example.myapp.model.UserInfoModel;
import com.example.myapp.service.MyappService;
import com.example.myapp.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/userInfo")
public class MyappController {

    @Autowired
    MyappService myappService;

    @GetMapping("/getAllUsers/v1")
    public ResponseEntity<Page<UserInfoDto>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        Page<UserInfoDto> userInfoList = myappService.getAllUsers(PageRequest.of(page, size));
        log.info("### getAllUsers: {}", userInfoList);
        return ResponseEntity.ok(userInfoList);
    }

    @GetMapping("/getUsersByName/v1")
    public ResponseEntity<Page<UserInfoDto>> getUsersByName(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size,
                                                         @RequestParam(name = "name") String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Param [name] cannot be empty");
        }
        Page<UserInfoDto> userInfoList = myappService.getUsersByName(name, PageRequest.of(page, size));
        log.info("### getUsersByName: {}", userInfoList);
        return ResponseEntity.ok(userInfoList);
    }

    @GetMapping("/getUserById/v1/{id}")
    public ResponseEntity<UserInfoDto> getUserById(@PathVariable Long id) {
        UserInfoDto userInfo = myappService.getUserById(id);
        log.info("### getUserById: {}", userInfo);
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/createUser/v1")
    public ResponseEntity<UserInfoDto> createUser(@RequestBody UserInfoDto userInfo) {
        if (StringUtils.isBlank(userInfo.getName())) {
            throw new IllegalArgumentException("Param [name] cannot be empty");
        }
        if (!CommonUtils.isValidEmail(userInfo.getEmail())) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (!CommonUtils.isValidPhoneNumber(userInfo.getMobileNum())) {
            throw new IllegalArgumentException("Invalid mobile number");
        }
        UserInfoDto createdUser = myappService.createUser(userInfo);
        log.info("### createUser: {}", createdUser);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/updateUser/v1/{id}")
    public ResponseEntity<UserInfoDto> updateUser(@PathVariable Long id, @RequestBody UserInfoDto userInfo) {
        UserInfoDto updatedUser = myappService.updateUser(id, userInfo);
        log.info("### updateUser: {}", updatedUser);
        return ResponseEntity.ok(updatedUser);
    }
}
