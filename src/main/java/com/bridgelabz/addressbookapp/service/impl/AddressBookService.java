package com.bridgelabz.addressbookapp.service.impl;


import com.bridgelabz.addressbookapp.dto.Contact;
import com.bridgelabz.addressbookapp.service.IAddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AddressBookService implements IAddressBookService {

    private List<Contact> contactList = new ArrayList<>();

    public List<Contact> getContacts() {
        log.info("getEmployee invoked");
        return contactList;
    }

    public Contact createContact(Contact contact) {
        contactList.add(contact);
        log.info("Contact created");
        return contact;
    }

    public Contact updateContact(int id) {
        Contact contact = findContactById(id);
        contact.setName(contact.getName());
        contact.setAddress(contact.getAddress());
        contact.setCity(contact.getCity());
        contact.setState(contact.getState());
        contact.setPhoneNo(contact.getPhoneNo());
        contact.setZip(contact.getZip());
        contactList.add(contact);
        log.info("Contact updated");
        return contact;
    }

    public String deleteContact(int id) {
        Contact contact = findContactById(id);
        contactList.remove(contact);
        return "Contact deleted successfully";
    }

    private Contact findContactById(int id) {
        return contactList.stream()
                .filter(contacts -> contacts.getId() == id).findFirst()
                .orElseThrow(() -> new RuntimeException("ID not found"));
    }
}
