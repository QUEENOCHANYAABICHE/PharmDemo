package com.example.pharmdemo.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UpdateResponse {
    private Long id;
    private String name;
    private String prodDate;
    private String expDate;
    private String drugType;
}
