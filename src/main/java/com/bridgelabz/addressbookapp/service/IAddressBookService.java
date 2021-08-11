package com.bridgelabz.addressbookapp.service;

import com.bridgelabz.addressbookapp.dto.Contact;

import java.util.List;

public interface IAddressBookService {

    public List<Contact> getContacts();
    public Contact createContact(Contact contact);
    public Contact updateContact(int id);
    public String deleteContact(int id);
}
