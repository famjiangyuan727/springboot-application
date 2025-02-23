package com.example.myapp.feign.impl;

import com.example.myapp.feign.ThirdPartyFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ThirdPartyFeignClientImpl implements ThirdPartyFeignClient {

    @Autowired
    ThirdPartyFeignClient thirdPartyFeignClient;

    @Override
    public List<Map<String, Object>> getCountryByName(String countryName) {
        return thirdPartyFeignClient.getCountryByName(countryName);
    }
}
