package com.example.pharmdemo.dto.respone;

import com.example.pharmdemo.models.Drugs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrugResponse {
    private Long id;
    private String name;
    private String prodDate;
    private String expDate;
    private String Type;

    public DrugResponse(List<Drugs> allDrugs) {
    }
}
