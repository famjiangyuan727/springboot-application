package com.example.myapp.service.impl;

import com.example.myapp.feign.ThirdPartyFeignClient;
import com.example.myapp.service.ThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Autowired
    ThirdPartyFeignClient thirdPartyFeignClient;

    @Override
    public List<Map<String, Object>> getCountryByName(String countryName) {
        return thirdPartyFeignClient.getCountryByName(countryName);
    }
}
