package com.bridgelabz.addressbookapp.service.impl;


import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.entity.Contact;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import com.bridgelabz.addressbookapp.service.IAddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class AddressBookService implements IAddressBookService {

    @Autowired
    public AddressBookRepository addressBookRepository;

    /**
     * Get all contacts from address book
     * @return
     */
    public List<Contact> getContacts() {
        log.info("getEmployee invoked");
        return addressBookRepository.findAll();
    }

    /**
     * Create new contact in the address book and save in database
     * @param contactDTO
     * @return
     */
    @Override
    public Contact createContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        log.info("Contact Created");
        return addressBookRepository.save(contact);
    }

    /**
     * Update address book contact by id and save updated contact
     * in database
     * @param contactDTO
     * @param id
     * @return
     */
    @Override
    public Contact updateContact(ContactDTO contactDTO, int id) {
       Contact contact = addressBookRepository.findById(id)
               .orElseThrow(() -> new AddressBookException("User id not found",
                       AddressBookException.ExceptionType.USER_NOT_FOUND));
       contact.setName(contactDTO.getName());
       contact.setAddress(contactDTO.getAddress());
       contact.setCity(contactDTO.getCity());
       contact.setState(contactDTO.getState());
       contact.setPhone(contactDTO.getPhoneNo());
       contact.setZip(contactDTO.getZip());
        return addressBookRepository.save(contact);
    }

    /**
     * Delete contact by id
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deleteContact(int id) {
        addressBookRepository.deleteById(id);
        return new ResponseDTO(id, "Contact deleted");
    }

}
