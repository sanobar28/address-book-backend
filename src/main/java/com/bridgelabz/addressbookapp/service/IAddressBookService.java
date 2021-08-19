package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.ContactDTO;

import java.util.List;

public interface IAddressBookService {

    List<ContactDTO> getContacts();
    ContactDTO createContact(ContactDTO contactDTO);
    ContactDTO updateContact(ContactDTO contactDTO, int id);
    String deleteContact(int id);
}
