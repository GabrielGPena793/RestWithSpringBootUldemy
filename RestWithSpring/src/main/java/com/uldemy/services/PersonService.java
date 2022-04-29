package com.uldemy.services;

import com.uldemy.converter.DozerConverter;
import com.uldemy.dto.PersonDTO;
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

    public PersonDTO create(PersonDTO personDTO){
        var personEntity = DozerConverter.parseObject(personDTO, Person.class);

        return DozerConverter.parseObject(personRepository.save(personEntity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO personDTO){
        var entity = personRepository.findById(personDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        return DozerConverter.parseObject(personRepository.save(entity), PersonDTO.class);
    }
    public PersonDTO findById(Long id){
        var person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        return DozerConverter.parseObject(person, PersonDTO.class);
    }

    public List<PersonDTO> findAll(){
        return DozerConverter.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }


}
