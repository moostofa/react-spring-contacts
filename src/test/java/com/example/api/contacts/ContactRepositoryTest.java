package com.example.api.contacts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void testThatFindByNameReturnsAContact() {
        // given
        Contact contact = new Contact("Java", "java8", "java8@gmail.com", "Oracle", "1.8",
                "Oracle Corporation");
        repository.save(contact);

        // when
        Contact expected = repository.findByName("Java").get();

        // then
        assertThat(contact).isEqualTo(expected);
    }

    @Test
    void testThatFindByNameReturnsNull() {
        Optional<Contact> contact = repository.findByName("Java");
        assertThat(contact).isEmpty();
    }
}
