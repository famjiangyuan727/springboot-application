package com.example.myapp.service;

import java.util.List;
import java.util.Map;

public interface ThirdPartyService {

    List<Map<String, Object>> getCountryByName(String countryName);
}
