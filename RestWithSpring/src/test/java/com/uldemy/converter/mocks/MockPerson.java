package com.uldemy.converter.mocks;

import com.uldemy.dto.PersonDTO;
import com.uldemy.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {


    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonDTO mockVO() {
        return mockVO(0);
    }

    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonDTO> mockVOList() {
        List<PersonDTO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockVO(i));
        }
        return persons;
    }

    private Person mockEntity(Integer number) {
        Person person = new Person();
        person.setAddress("Addres Test" + number);
        person.setFirstName("First Name Test" + number);
        person.setGender(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setLastName("Last Name Test" + number);
        return person;
    }

    private PersonDTO mockVO(Integer number) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setAddress("Addres Test" + number);
        personDTO.setFirstName("First Name Test" + number);
        personDTO.setGender(((number % 2)==0) ? "Male" : "Female");
        personDTO.setId(number.longValue());
        personDTO.setLastName("Last Name Test" + number);
        return personDTO;
    }

}
