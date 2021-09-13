package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.dto.StateDTO;
import com.bridgelabz.addressbookapp.entity.State;

import java.util.List;

public interface IAddressBookService {

    List<ContactDTO> getContacts();
    ContactDTO createContact(ContactDTO contactDTO);
    ContactDTO updateContact(int id, ContactDTO contactDTO);
    String deleteContact(int id);

    List<StateDTO> getStateAndCity();
}
