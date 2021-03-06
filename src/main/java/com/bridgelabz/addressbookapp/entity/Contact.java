package com.bridgelabz.addressbookapp.entity;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"phone"})})
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

//    @CreationTimestamp
//    private LocalDateTime createdDate;
//
//    @UpdateTimestamp
//    private LocalDateTime updatedDate;
}
