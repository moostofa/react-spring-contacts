package com.example.api.contacts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    private final ContactService service;

    @Autowired
    public ContactController(ContactService service) {
        this.service = service;
    }

    // Return a specific contact by Id, which will be passed as a request param
    @GetMapping("/get/{contactId}")
    public List<Contact> getContact(@PathVariable("contactId") Integer id) {
        return service.get(id);
    }

    // Return a list of all contacts in DB
    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        return service.getAll();
    }

    // Save a new contact to DB
    @PostMapping("/add")
    public Contact addContact(@RequestBody ContactRequestModel details) {
        Contact contact = new Contact(details);
        return service.saveContact(contact);
    }

    // Update the name field of a contact
    @PutMapping("/update/{contactId}")
    public Contact updateContact(@PathVariable("contactId") Integer id,
            @RequestParam(required = false) String name) {
        return service.updateContact(id, name);
    }

    // Delete a contact by ID
    @DeleteMapping("/delete/{contactId}")
    public void deleteContact(@PathVariable("contactId") Integer id) {
        service.deleteContact(id);
    }
}
