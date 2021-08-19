package com.bridgelabz.addressbookapp.controller;

import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.service.IAddressBookService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



@RunWith(MockitoJUnitRunner.class)
public class AddressBookControllerTest {

    @InjectMocks
    private AddressBookController addressBookController;

    @Mock
    private IAddressBookService addressBookService;

    @Test
    public void getAddressBookContactsTest() {
        when(addressBookService.getContacts()).thenReturn(Lists.newArrayList(new ContactDTO(), new ContactDTO()));
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.getAddressBookContacts();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("All contacts in address book", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    @Test
    public void createContactTest() {
        //int id = 1;
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("Sanobar");
        contactDTO.setAddress("DBATU Lonere");
        contactDTO.setCity("Mangaon");
        contactDTO.setState("Maharashtra");
        contactDTO.setPhone("7860936702");
        contactDTO.setZip("402103");

        when(addressBookService.createContact(contactDTO)).thenReturn(contactDTO);
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.addContact(contactDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Contact created in address book", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    @Test
    public void updateContactTest() {
        int id = 1;
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("Sanobar");
        contactDTO.setAddress("DBATU Lonere");
        contactDTO.setCity("Mangaon");
        contactDTO.setState("Maharashtra");
        contactDTO.setPhone("7860936702");
        contactDTO.setZip("402103");

        when(addressBookService.updateContact(contactDTO, id)).thenReturn(new ContactDTO());
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.updateContact(id, contactDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Contact updated successfully", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    @Test
    public void deleteContactTest() {
        int id = 1;
        when(addressBookService.deleteContact(id)).thenReturn("Contact deleted");
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.deleteContact(id);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Contact deleted successfully", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }
}
