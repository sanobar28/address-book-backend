package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.entity.Contact;

import java.util.List;

public interface IAddressBookService {

    List<Contact> getContacts();
    Contact createContact(ContactDTO contactDTO);
    Contact updateContact(ContactDTO contactDTO, int id);
    ResponseDTO deleteContact(int id);
}
