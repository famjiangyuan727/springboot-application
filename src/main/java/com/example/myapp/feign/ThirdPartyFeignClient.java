package com.example.myapp.feign;

import com.example.myapp.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "thirdPartyFeignClient",
        url = "https://restcountries.com/v3.1",
        configuration = FeignClientConfig.class
)
public interface ThirdPartyFeignClient {

    @GetMapping("/name/{countryName}")
    List<Map<String, Object>> getCountryByName(@PathVariable("countryName") String countryName);
}
