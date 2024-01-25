package com.example.pharmdemo.controller;

import com.example.pharmdemo.dto.request.DrugRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.DrugResponse;
import com.example.pharmdemo.service.PharmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.pharmdemo.utils.PharmDemoEndpoints.*;


@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class PharmDemoController {
    private final PharmService pharmService;

    @PostMapping(CREATE_DRUG)
    public ResponseEntity<ApiResponse<DrugResponse>> createDrugs(@RequestBody DrugRequest drugRequest){
    return new ResponseEntity<>(pharmService.createDrugs(drugRequest),HttpStatus.CREATED);
    }


    @PutMapping(UPDATE_DRUG)
    public ResponseEntity<ApiResponse<DrugResponse>> updateDrugs(@RequestBody DrugRequest drugRequest){
        return new ResponseEntity<>(pharmService.updateDrugs(drugRequest),HttpStatus.OK);
    }

    @GetMapping(GET_ALL_DRUGS)
    public ResponseEntity<ApiResponse<List<DrugResponse>>> getAllDrugs(){
        return new ResponseEntity<>(pharmService.getAllDrugs(),HttpStatus.OK);
    }

    @GetMapping(GET_DRUG)
    public ResponseEntity<ApiResponse<DrugResponse>> getDrug(@PathVariable ("name") String name){
        return new ResponseEntity<>(pharmService.getDrug(name),HttpStatus.OK);
    }

    @DeleteMapping(DELETE_DRUG)
    public ResponseEntity<ApiResponse<String>> deleteDrugs(@RequestBody DrugRequest drugRequest){
        return new ResponseEntity<>(pharmService.deleteDrugs(drugRequest),HttpStatus.OK);
    }

}
