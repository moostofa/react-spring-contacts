package com.example.api.contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.api.contacts.exceptions.BadRequestException;
import com.example.api.contacts.exceptions.ContactNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository repository;

    @Autowired
    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    public List<Contact> get(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        return repository.findAllById(ids);
    }

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact saveContact(Contact details) {

        // check if the new contact's name already exists - prevent duplicates
        Optional<Contact> nameTaken = repository.findByName(details.getName());
        if (nameTaken.isPresent()) {
            throw new BadRequestException(
                    "A contact already has the name " + details.getName() + ".");
        }
        return repository.save(details);
    }

    @Transactional
    public Contact updateContact(Integer id, String name) {

        // check if contact exists
        Optional<Contact> contact = repository.findById(id);
        if (!contact.isPresent()) {
            throw new ContactNotFoundException("A contact with an id of " + id + " was not found.");
        }

        // check if String name is valid
        if (name == null || name.length() == 0 || contact.get().getName().equals(name)) {
            throw new BadRequestException("Cannot update Contact entity (" + id + ")'s name field; "
                    + name + "(name) is invalid or already taken.");
        }
        contact.get().setName(name);
        return contact.get();
    }

    public void deleteContact(Integer id) {
        Optional<Contact> contactToDelete = repository.findById(id);
        if (!contactToDelete.isPresent()) {
            throw new ContactNotFoundException(
                    "Failed to delete contact with an id of " + id + "; Contact entity not found.");
        }
        repository.delete(contactToDelete.get());
    }
}
