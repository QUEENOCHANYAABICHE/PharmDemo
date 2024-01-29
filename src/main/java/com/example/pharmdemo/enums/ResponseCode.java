package com.example.pharmdemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResponseCode {
    DRUG_CREATED_SUCCESSFULLY(405,"Created drug successfully"),
    DRUG_UPDATED_SUCCESSFULLY(406,"Updated drug successfully"),
    DRUG_DELETED_SUCCESSFULLY(407,"Deleted drug successfully"),
    GOT_DRUG_SUCCESSFULLY(408,"Got drug successfully"),
    GOT_ALL_DRUG_SUCCESSFULLY(409,"Got all drugs successfully"),
    USER_CREATED_SUCCESSFULLY(500,"Created user successfully");


    private int code;
    private String message;


}
