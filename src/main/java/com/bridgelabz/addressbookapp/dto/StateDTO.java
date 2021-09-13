package com.bridgelabz.addressbookapp.dto;

import com.bridgelabz.addressbookapp.entity.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateDTO {
    private int id;
    private String name;
    private List<String> city;
}
