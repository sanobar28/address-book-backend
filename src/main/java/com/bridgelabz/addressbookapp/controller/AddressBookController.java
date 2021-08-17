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
import com.bridgelabz.addressbookapp.service.IAddressBookService;
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
    private IAddressBookService addressBookService;

    /**
     * To Retrieve all contacts from address book
     * @return
     */
    @GetMapping(value = "/getcontacts")
    public ResponseEntity<ResponseDTO> getAddressBookContacts(){
        List<ContactDTO> contactList = addressBookService.getContacts();
        return new ResponseEntity<>(new ResponseDTO(contactList, "All contacts in address book"), HttpStatus.OK);
    }

    /**
     * To create new valid contact in address book
     * @param contactDTO
     * @param e
     * @return
     */
    @PostMapping(value = "/addcontact")
    public ResponseEntity<ResponseDTO> addContact(@RequestBody @Valid ContactDTO contactDTO, BindingResult e) {
        if (e.hasErrors()) {
            List<String> error = e.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseDTO(error, "Validation Error Occurred"),
                    HttpStatus.BAD_REQUEST);
        }
        ContactDTO contactDTO1 = addressBookService.createContact(contactDTO);
        return new ResponseEntity<>(new ResponseDTO(contactDTO1, "Contact created in address book"), HttpStatus.CREATED);
    }

    /**
     * To update contact in address book by id
     * @param id
     * @param contactDTO
     * @return
     */
    @PutMapping(value = "/updatecontact")
    public ResponseEntity<ResponseDTO> updateContact(@RequestParam (name = "id") int id,
                                                    @Valid @RequestBody ContactDTO contactDTO) {
        ContactDTO contactDTO1 = addressBookService.updateContact(contactDTO, id);
        return new ResponseEntity<>(new ResponseDTO(contactDTO1, "Contact updated successfully"), HttpStatus.OK);
    }

    /**
     * Delete contact in address book by id
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> deleteContact(@RequestParam (name = "id") int id) {
        return new ResponseEntity<>(new ResponseDTO(addressBookService.deleteContact(id),
                "Contact deleted successfully"), HttpStatus.OK);
    }
}
