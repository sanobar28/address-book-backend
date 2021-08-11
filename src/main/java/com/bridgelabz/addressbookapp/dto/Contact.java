package com.bridgelabz.addressbookapp.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class Contact {

    private int id;

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}", message = "Invalid Name")
    private String name;

    @Pattern(regexp = "^[a-zA-Z0-9\\s]{3,}$", message = "Invalid Address")
    private String address;
    private String city;
    private String state;

    @Pattern(regexp = "^[7-9][0-9]{9}$", message = "Invalid Phone Number")
    private String phoneNo;

    @Pattern(regexp = "^[1-9][0-9]{5,}$")
    private String zip;

}
