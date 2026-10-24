package com.springapp.simpleapi.repository;

import com.springapp.simpleapi.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepository {

    private final List<Contact> contactList = new ArrayList<>();
    private int idCounter = 1;

    public List<Contact> findAllContacts(){
        return new ArrayList<>(contactList);
    }

    public Optional<Contact> findContactById(int id){
        return contactList.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst();
    }

    public Contact save(Contact contact){
        if(contact.getId() == null || contact.getId() <= 0){
            contact.setId(idCounter);
            contactList.add(contact);
        }else {
            // delete
            deleteContactById(contact.getId());
            contactList.add(contact);
        }
        return contact;
    }

    public void deleteContactById(int id){
        contactList.removeIf(contact -> contact.getId() == id);
    }
}
