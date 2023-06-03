package com.liftlife.liftlife.dto;

import com.liftlife.liftlife.common.CoachSpecialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CoachDto {
    private String id;
    private String firstName;
    private String lastName;
    private CoachSpecialization specialization;
    private String description;
    private String email;
}
