/**
 * @author Sanobar Mujawar
 * @since 14.08.21
 *
 * Purpose: Address Book app to get, add, update, and delete
 * contacts from database using Rest-API calls.
 */

package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.entity.Contact;
import com.bridgelabz.addressbookapp.service.impl.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/addressbook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseDTO> getAddressBookContacts(){
        List<Contact> contactList = addressBookService.getContacts();
        return new ResponseEntity<>(new ResponseDTO(contactList, "All contacts in address book"), HttpStatus.OK);
    }

    @PostMapping(value = "/addcontact")
    public ResponseEntity<ResponseDTO> addContact(@RequestBody @Valid ContactDTO contactDTO, BindingResult e) {
        if (e.hasErrors()) {
            List<String> error = e.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseDTO(error, "Validation Error Occurred"),
                    HttpStatus.BAD_REQUEST);
        }

        Contact contact = addressBookService.createContact(contactDTO);
        return new ResponseEntity<>(new ResponseDTO(contact, "Contact created in address book"), HttpStatus.CREATED);
    }

    
    @PutMapping(value = "/updatecontact")
    public ResponseEntity<ResponseDTO> updateContact(@RequestParam (name = "id") int id,
                                                    @Valid @RequestBody ContactDTO contactDTO) {
        Contact contact = addressBookService.updateContact(contactDTO, id);
        return new ResponseEntity<>(new ResponseDTO(contact, "Contact updated successfully"), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> deleteContact(@RequestParam (name = "id") int id) {
        return new ResponseEntity<>(new ResponseDTO(addressBookService.deleteContact(id),
                "Contact deleted successfully"), HttpStatus.OK);
    }
}
