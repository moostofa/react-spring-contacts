package com.example.api.contacts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Optional<Contact> findByName(String name);
}
