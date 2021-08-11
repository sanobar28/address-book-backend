package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.Contact;
import com.bridgelabz.addressbookapp.service.impl.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping(value = "/get")
    public ResponseEntity<List<Contact>> getAddressBookContacts(){
        return new ResponseEntity<>(addressBookService.getContacts(), HttpStatus.OK);
    }

    @PostMapping(value = "/addcontact")
    public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) {
        return new ResponseEntity<>(addressBookService.createContact(contact), HttpStatus.OK);
    }

    @PutMapping(value = "/updatecontact")
    public ResponseEntity<Contact> updateContact(@RequestParam (name = "id") int id,
                                                 @Valid @RequestBody Contact contact) {
        return new ResponseEntity<>(addressBookService.updateContact(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteContact(@RequestParam (name = "id") int id) {
        return new ResponseEntity<>(addressBookService.deleteContact(id), HttpStatus.OK);
    }
}
