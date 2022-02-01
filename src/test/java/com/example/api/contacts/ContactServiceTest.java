package com.example.api.contacts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.example.api.contacts.exceptions.BadRequestException;
import com.example.api.contacts.exceptions.ContactNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class ContactServiceTest {

    @Mock
    private ContactRepository mockRepository;

    private ContactService testService;
    private Contact sample;

    public ContactServiceTest() {
    }

    @BeforeAll
    void sampleSetup() {
        sample = new Contact("Java", "java8", "java8@gmail.com", "Oracle", "1.8",
                "Oracle Corporation");
    }

    @BeforeEach
    void mockSetup() {
        testService = new ContactService(mockRepository);
    }

    @Test
    void testAddContact() {
        // given
        // this.sample

        // when
        testService.saveContact(sample);

        // then
        // capture the argument passed into .save()
        ArgumentCaptor<Contact> argCaptor = ArgumentCaptor.forClass(Contact.class);
        verify(mockRepository).save(argCaptor.capture());

        Contact expected = argCaptor.getValue();
        assertThat(sample).isEqualTo(expected);
    }

    @Test
    void testAddWillThrowWhenNameIsTaken() {
        // given
        // this.sample
        given(mockRepository.findByName(anyString())).willReturn(Optional.of(sample));

        // when
        // then
        assertThatThrownBy(() -> testService.saveContact(sample))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("A contact already has the name " + sample.getName() + ".");

        verify(mockRepository, never()).save(any());
    }

    @Test
    void testRetrievingSingleContact() {
        // given
        // when
        testService.get(anyInt());

        // then
        verify(mockRepository).findAllById(anyList());
    }

    @Test
    void testRetrievingAllContacts() {
        // given
        // when
        testService.getAll();

        // then
        verify(mockRepository).findAll();
    }

    @Test
    void testUpdate() {
        // given
        // this.sample,
        Integer id = sample.getId();
        String oldName = sample.getName();

        given(mockRepository.findById(id)).willReturn(Optional.of(sample));

        // when
        String newName = "Python";
        Contact expected = testService.updateContact(id, newName);

        // then
        assertThat(sample).isEqualTo(expected);
        assertThat(sample.getName()).isNotEqualTo(oldName);
    }

    @Test
    void testUpdateWillThrowWhenNameIsTakenOrTheSame() {
        // given
        // this.sample
        Integer id = sample.getId();
        given(mockRepository.findById(id)).willReturn(Optional.of(sample));

        // when
        // then
        String newName = sample.getName();
        assertThatThrownBy(() -> testService.updateContact(id, newName))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Cannot update Contact entity");
    }

    @Test
    void testDeleteContact() {
        // given
        // this.sample
        Integer id = sample.getId();
        given(mockRepository.findById(id)).willReturn(Optional.of(sample));

        // when
        testService.deleteContact(id);

        // then
        // capture the argument passed into .save()
        ArgumentCaptor<Contact> argCaptor = ArgumentCaptor.forClass(Contact.class);
        verify(mockRepository).delete(argCaptor.capture());

        Contact deleted = argCaptor.getValue();
        assertThat(sample).isEqualTo(deleted);
    }

    @Test
    void testDeleteContactWhenIdNotFound() {
        // given
        int id = 1;
        given(mockRepository.findById(id)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> testService.deleteContact(id))
                .isInstanceOf(ContactNotFoundException.class)
                .hasMessageContaining("Failed to delete contact");
    }
}
