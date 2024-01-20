package com.example.pharmdemo.models;


import com.example.pharmdemo.enums.DrugsType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Drugs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Instant prodDate;
    private Instant expDate;
    @Enumerated(EnumType.STRING)
    private DrugsType drugsType;

}
