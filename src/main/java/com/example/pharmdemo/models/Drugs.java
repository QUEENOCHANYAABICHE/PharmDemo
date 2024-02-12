package com.example.pharmdemo.models;


import com.example.pharmdemo.enums.DrugsType;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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
    private LocalDateTime prodDate;
    private LocalDateTime expDate;
    @Enumerated(EnumType.STRING)
    private DrugsType drugsType;

//    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "id")

//    private User user;
}
