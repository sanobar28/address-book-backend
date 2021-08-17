package com.bridgelabz.addressbookapp.service.impl;


import com.bridgelabz.addressbookapp.builder.AddressBuilder;
import com.bridgelabz.addressbookapp.dto.ContactDTO;
import com.bridgelabz.addressbookapp.dto.ResponseDTO;
import com.bridgelabz.addressbookapp.entity.Contact;
import com.bridgelabz.addressbookapp.exception.AddressBookException;
import com.bridgelabz.addressbookapp.repository.AddressBookRepository;
import com.bridgelabz.addressbookapp.service.IAddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class AddressBookService implements IAddressBookService {

    @Autowired
    public AddressBookRepository addressBookRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddressBuilder addressBuilder;

    /**
     * Get all contacts from address book
     *
     * @return
     */
    @Override
    public List<ContactDTO> getContacts() {
        log.info("getEmployee invoked");
        return addressBookRepository.findAll().stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Create new contact in the address book and save in database
     *
     * @param contactDTO
     * @return
     */
    @Override
    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = addressBuilder.buildContact(contactDTO);
        contact = addressBookRepository.save(contact);
        contact.setId(contact.getId());
        log.info("Contact Created");
        return contactDTO;
    }

    /**
     * Update address book contact by id and save updated contact
     * in database
     *
     * @param contactDTO
     * @param id
     * @return
     */
    @Override
    public ContactDTO updateContact(ContactDTO contactDTO, int id) {
        Contact contact = addressBookRepository.findById(id)
                .orElseThrow(() -> new AddressBookException("User id not found",
                        AddressBookException.ExceptionType.USER_NOT_FOUND));
       String[] ignoreFields = {"id", "name"};
       BeanUtils.copyProperties(contactDTO, contact, ignoreFields);
       addressBookRepository.save(contact);
       ContactDTO contactDTOResponse = modelMapper.map(contact, ContactDTO.class);
       return contactDTOResponse;
    }

    /**
     * Delete contact by id
     *
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deleteContact(int id) {
        addressBookRepository.deleteById(id);
        return new ResponseDTO(id, "Contact deleted");
    }

}
