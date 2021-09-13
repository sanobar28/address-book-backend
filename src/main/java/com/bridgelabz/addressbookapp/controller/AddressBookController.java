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
import com.bridgelabz.addressbookapp.dto.StateDTO;
import com.bridgelabz.addressbookapp.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
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
     * @param
     * @return
     */
    @PostMapping(value = "/addcontact")
    public ResponseEntity<ResponseDTO> addContact(@RequestBody @Valid ContactDTO contactDTO) {
        ContactDTO contactDTO1 = addressBookService.createContact(contactDTO);
        return new ResponseEntity<>(new ResponseDTO(contactDTO1, "Contact created in address book"), HttpStatus.CREATED);
    }

    /**
     * To update contact in address book by id
     * @param id
     * @param contactDTO
     * @return
     */
    @PutMapping(value = "/updatecontact/{id}")
    public ResponseEntity<ResponseDTO> updateContact(@PathVariable (name = "id") int id,
                                                     @RequestBody @Valid ContactDTO contactDTO) {
        ContactDTO contactDTO1 = addressBookService.updateContact(id, contactDTO);
        return new ResponseEntity<>(new ResponseDTO(contactDTO1, "Contact updated successfully"), HttpStatus.OK);
    }

    /**
     * Delete contact in address book by id
     * @param id
     * @return
     */
    @DeleteMapping(value = "deletecontact/{id}")
    public ResponseEntity<ResponseDTO> deleteContact(@PathVariable (name = "id") int id) {
        return new ResponseEntity<>(new ResponseDTO(addressBookService.deleteContact(id),
                "Contact deleted successfully"), HttpStatus.OK);
    }


    /**
     * To get state and there cities from state repository
     * @return
     */
    @GetMapping(value = "/getStateAndCity")
    public ResponseEntity<ResponseDTO> getStateDetails() {
        List<StateDTO> stateList = addressBookService.getStateAndCity();
        ResponseDTO responseDTO = new ResponseDTO(stateList, "Fetched all State Details");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
