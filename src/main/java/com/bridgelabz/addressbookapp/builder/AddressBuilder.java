package com.bridgelabz.addressbookapp.builder;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.entity.Contact;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressBuilder {

    public Contact buildContact(ContactDTO contactDTO){
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactDTO, contact);
        return contact;
    }
}
