package com.example.pharmdemo.service;


import com.example.pharmdemo.dto.request.DrugRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.DrugResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PharmService {


    ApiResponse<DrugResponse> createDrugs(@RequestBody DrugRequest drugRequest);

    ApiResponse<DrugResponse> updateDrugs(DrugRequest drugRequest);

    ApiResponse<List<DrugResponse>> getAllDrugs();

    ApiResponse<DrugResponse> getDrug(@PathVariable String name);

    ApiResponse<String> deleteDrugs(@RequestBody DrugRequest drugRequest);
}
