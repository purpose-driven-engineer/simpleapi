package com.springapp.simpleapi.service;

import com.springapp.simpleapi.dto.ApiResponse;
import com.springapp.simpleapi.model.Contact;
import com.springapp.simpleapi.repository.ContactRepository;
import org.springframework.aop.aspectj.AspectJPrecedenceInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public ApiResponse<List<Contact>> getAllContacts(){
        List<Contact> contactList = contactRepository.findAllContacts();
        return new ApiResponse<>(200, "All contacts found!", contactList);
    }

    public ApiResponse<Contact> addContact (Contact contact){
        Contact savedContact = contactRepository.save(contact);

        return new ApiResponse<>(201,"Contact Saved!", savedContact);
    }

    public ApiResponse<?> deleteContact(int id){
        if(contactRepository.findContactById(id).isPresent()){
            contactRepository.deleteContactById(id);
            return new ApiResponse<>(204, "Contact deleted!", null);
        }
        return new ApiResponse<>(404, "Contact not found!,", null);
    }

    public ApiResponse<Contact> updateContact(int id, Contact updatedContact){
        return contactRepository.findContactById(id)
                .map(existingContact -> {
                    existingContact.setName(updatedContact.getName());
                    existingContact.setPhoneNumber(updatedContact.getPhoneNumber());
                    contactRepository.save(existingContact);
                    return new ApiResponse<>(200, "Contact Updated Successfully!", existingContact);
                })
                .orElse(new ApiResponse<>(404, "Contact Not Found!", null));
    }

}
