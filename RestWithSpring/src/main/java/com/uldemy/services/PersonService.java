package com.uldemy.services;

import com.uldemy.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    public void delete(String id){
    }

    public Person create(Person person){
        return person;
    }

    public Person update(Person person){
        return person;
    }
    public Person findById(String id){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Gabriel");
        person.setLastName("Gomes");
        person.setAddress("Rua do vento");
        person.setGender("Masculino");
        return person;
    }

    public List<Person> findAll(){
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            Person person = mockPersons(i);
            persons.add(person);
        }
        return persons;
    }

    public Person mockPersons(int i){
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name " + i);
        person.setAddress("Some address in Brazil " + i);
        person.setGender(i % 2 == 0 ? "Masculino" : "Feminino" );
        return person;
    }
}
