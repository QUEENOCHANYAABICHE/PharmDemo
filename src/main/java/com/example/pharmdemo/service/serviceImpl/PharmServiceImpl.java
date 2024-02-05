package com.example.pharmdemo.service.serviceImpl;


import com.example.pharmdemo.dto.request.DrugRequest;
import com.example.pharmdemo.dto.respone.ApiResponse;
import com.example.pharmdemo.dto.respone.DrugResponse;
import com.example.pharmdemo.enums.DrugsType;
import com.example.pharmdemo.enums.ResponseCode;
import com.example.pharmdemo.exceptions.DrugAlreadyExistsException;
import com.example.pharmdemo.exceptions.DrugDoesNotExistException;
import com.example.pharmdemo.models.Drugs;
import com.example.pharmdemo.repository.PharmRepository;
import com.example.pharmdemo.service.PharmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmServiceImpl implements PharmService {

    private final PharmRepository pharmRepository;

    @Override
    public ApiResponse<DrugResponse> createDrugs(DrugRequest drugRequest) {
        //create a new object using drugRequest by setting the fields of the object
        //After setting it, create an object of ApiResponse & drugResponse
        //return the apiResponse object


        // using the request to check if the details from the drugRequest exist in the database already to avoid duplicate
        // if data from drugRequest is already existing in database throw an exception else map the drugRequest to the Drug entity
        //save

        // setting up the drugResponse
        // set and return a new apiResponse

        Optional<Drugs> drugExist = pharmRepository.findByName(drugRequest.getName());


        if(drugExist.isPresent()){
            throw new DrugAlreadyExistsException("drug already exist in database");
        }


        Drugs drugs = Drugs.builder()
                .name(drugRequest.getName())
                .drugsType(DrugsType.valueOf(drugRequest.getDrugType()))
                .prodDate(LocalDateTime.now())
                .expDate(LocalDateTime.now().plusSeconds(500))
                .build();
        pharmRepository.save(drugs);

        DrugResponse drugResponse = DrugResponse.builder()
                .id(drugs.getId())
                .name(drugs.getName())
                .expDate(drugs.getExpDate().toString())
                .prodDate(drugs.getProdDate().toString())
                .Type(drugs.getDrugsType().toString())
                .build();

        ApiResponse apiresponse = ApiResponse.builder()
                .statusCode(ResponseCode.DRUG_CREATED_SUCCESSFULLY.getCode())
                .data(drugResponse)
                .message(ResponseCode.DRUG_CREATED_SUCCESSFULLY.getMessage())
                .build();
        return apiresponse;
        //return new ApiResponse<>(ResponseCode.DRUG_CREATED_SUCCESSFULLY.getMessage(),Instant.now(),ResponseCode.DRUG_CREATED_SUCCESSFULLY.getCode(), drugResponse);
    }

    @Override
    public ApiResponse updateDrugs(DrugRequest drugRequest) {
        //check if the drug exists
        //if it does, update then save in the repository
        //if not throw an error
        //set drug response & Apiresponse
        //return Apiresponse
        Optional<Drugs> findDrug = pharmRepository.findByName(drugRequest.getName());
        if(findDrug.isEmpty()){
            throw new DrugDoesNotExistException("The drug does not exist");
        }
        Drugs drugs = Drugs.builder()
                .name(drugRequest.getName())
                .drugsType(DrugsType.valueOf(drugRequest.getDrugType()))
                .prodDate(LocalDateTime.now())
                .expDate(LocalDateTime.now().plusSeconds(500))
                .build();
        pharmRepository.save(drugs);

         DrugResponse drugResponse = DrugResponse.builder()
                 .id(drugs.getId())
                 .name(drugs.getName())
                 .Type(drugs.getDrugsType().toString())
                 .expDate(drugs.getExpDate().toString())
                 .prodDate(drugs.getProdDate().toString())
                 .build();

        return ApiResponse.builder()
                .statusCode(ResponseCode.DRUG_UPDATED_SUCCESSFULLY.getCode())
                .data(drugResponse)
                .message(ResponseCode.DRUG_UPDATED_SUCCESSFULLY.getMessage())
                .build();


//        UpdateResponse updateResponse = UpdateResponse.builder()
////                .expDate(Instant.now().toString())
////                .prodDate(Instant.now().toString())
//                .drugType(drugRequest.getDrugType())
//                .name(drugRequest.getName())
//                .build();
//        pharmRepo.save(updateResponse);


    }

    @Override
    public ApiResponse<List<DrugResponse>> getAllDrugs() {
        //find all drugs in the database and put it in a list
        //set the drug and Api Response to return a list of drugs
        //return the apiResponse holding the list of drugs

        List<Drugs> allDrugs = pharmRepository.findAll();

        List<DrugResponse>  responses = allDrugs.stream()
                .map(drug -> DrugResponse.builder()
                        .id(drug.getId())
                        .name(drug.getName())
                        .Type(drug.getDrugsType().toString())
                        .expDate(drug.getExpDate().toString())
                        .prodDate(drug.getProdDate().toString())
                        .build()
                ).collect(Collectors.toList());

        return new ApiResponse<>(ResponseCode.DRUG_CREATED_SUCCESSFULLY.getMessage(),ResponseCode.DRUG_CREATED_SUCCESSFULLY.getCode(),responses);
    }

    @Override
    public ApiResponse<DrugResponse> getDrug(String name) {
        //check for a drug by its name, throw an error if absent
        //build drug with the values passed in the request
        //set ApiResponse, DrugResponse
        //create object of apiresponse & return it
        Optional<Drugs> findDrug = pharmRepository.findByName(name);
        if (findDrug.isEmpty()) {
            throw new DrugDoesNotExistException("The drug does not exist");

        }
        DrugResponse drugResponse = DrugResponse.builder()
                .id(findDrug.get().getId())
                .name(findDrug.get().getName())
                .Type(findDrug.get().getDrugsType().toString())
                .prodDate(findDrug.get().getProdDate().toString())
                .expDate(findDrug.get().getExpDate().toString())
                .build();

        return new ApiResponse<>(ResponseCode.DRUG_CREATED_SUCCESSFULLY.getMessage(),ResponseCode.DRUG_CREATED_SUCCESSFULLY.getCode(), drugResponse);
    }

    @Override
    public ApiResponse<String> deleteDrugs(DrugRequest drugRequest) {
        //find the drug by id from the db
        //If found, delete, else throw an error
        Optional<Drugs> findDrugs = pharmRepository.findByName(drugRequest.getName());
        if (!findDrugs.isPresent()) {
            throw new DrugDoesNotExistException("The drug does not exist");
        }

        pharmRepository.deleteById(findDrugs.get().getId());

        return new ApiResponse<>(ResponseCode.DRUG_DELETED_SUCCESSFULLY.getMessage(),ResponseCode.DRUG_DELETED_SUCCESSFULLY.getCode(), "PROCESSED SUCCESSFUL");
    }





}
