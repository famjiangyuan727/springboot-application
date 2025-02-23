package com.example.myapp.controller;

import com.example.myapp.service.ThirdPartyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/third-party-api")
public class ThirdPartyController {

    @Autowired
    ThirdPartyService thirdPartyService;

    @GetMapping("/getCountry/v1/{countryName}")
    public ResponseEntity<List<Map<String, Object>>> getCountryByName(@PathVariable String countryName) {
        log.info("### calling third party api : {}", countryName);
        List<Map<String, Object>> response = thirdPartyService.getCountryByName(countryName);
        log.info("### response: {}", response);
        return ResponseEntity.ok(response);
    }
}
