package com.bridgelabz.addressbookapp.entity;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String zip;

    public Contact(){
    }

    public Contact(ContactDTO contactDTO){
        this.name = contactDTO.getName();
        this.address = contactDTO.getAddress();
        this.city = contactDTO.getCity();
        this.state = contactDTO.getState();
        this.phone = contactDTO.getPhoneNo();
        this.zip = contactDTO.getZip();
    }
}
