package com.springapp.simpleapi.controller;

import com.springapp.simpleapi.dto.ApiResponse;
import com.springapp.simpleapi.model.Contact;
import com.springapp.simpleapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/greet")
    public String hello(){
        return "Hello World! This is a contact API ";
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Contact>>> getAllContacts(){
        return ResponseEntity.ok(contactService.getAllContacts());
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Contact>> createContact(@RequestBody Contact contact){
        return ResponseEntity.ok(contactService.addContact(contact));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> deleteContact(@PathVariable int id){
        return ResponseEntity.ok(contactService.deleteContact(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Contact>> updateContact(@PathVariable int id, @RequestBody Contact contactData){
        ApiResponse<Contact> response = contactService.updateContact(id, contactData);

        return ResponseEntity.ok(response);
    }
}
