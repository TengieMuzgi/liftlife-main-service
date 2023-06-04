package com.liftlife.liftlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClientDto {
    private String id;
    private String firstName;
    private String lastName;
    private Date registerDate;
}
