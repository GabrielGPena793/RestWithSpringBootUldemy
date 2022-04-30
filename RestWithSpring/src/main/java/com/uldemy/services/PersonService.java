package com.uldemy.services;

import com.uldemy.converter.DozerConverter;
import com.uldemy.dto.V2.PersonDTOV2;
import com.uldemy.dto.v1.PersonDTO;
import com.uldemy.exceptions.ResourceNotFoundException;
import com.uldemy.model.Person;
import com.uldemy.repositories.PersonRepository;
import org.springframework.beans.BeanUtils;
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

    //Simulando um troca de versão de rotas
    public PersonDTOV2 createV2(PersonDTOV2 personDTOV2){
        Person person = new Person();
        BeanUtils.copyProperties(personDTOV2, person);
        BeanUtils.copyProperties(personRepository.save(person), personDTOV2);
        return personDTOV2;
    }
    //---------

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
