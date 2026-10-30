package com.springapp.simpleapi.controller;

import com.springapp.simpleapi.dto.ApiResponse;
import com.springapp.simpleapi.model.Contact;
import com.springapp.simpleapi.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
