package com.uldemy.services;

import com.uldemy.exceptions.ResourceNotFoundException;
import com.uldemy.model.Person;
import com.uldemy.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public void delete(Long id){
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        personRepository.delete(person);

    }

    public Person create(Person person){
        return personRepository.save(person);
    }

    public Person update(Person person){
        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return personRepository.save(entity);
    }
    public Person findById(Long id){
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }


}
