package com.uldemy.controllers;

import com.uldemy.dto.v1.PersonDTO;
import com.uldemy.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = {"application/json","application/xml", "application/x-yaml"})
    public List<PersonDTO> findAll(){
        List<PersonDTO> personDTOS = personService.findAll();

        if (personDTOS.isEmpty()){
            throw  new ResourceAccessException("Not Have items");
        }

        for (PersonDTO person: personDTOS) {
            Long id = person.getId();
            person.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        }

        return personDTOS;
    }

    @GetMapping(value = "/{id}", produces = {"application/json","application/xml", "application/x-yaml"})
    public PersonDTO findById(@PathVariable Long id){
        return personService.findById(id)
                .add(linkTo(methodOn(PersonController.class).findAll())
                .withRel("List Persons"));
    }


    @PostMapping(produces = {"application/json","application/xml", "application/x-yaml"},
            consumes = {"application/json","application/xml", "application/x-yaml"})
    public PersonDTO create(@RequestBody PersonDTO personDTO){
        var person = personService.create(personDTO);
        return person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());

    }
//
//    //Simulando um troca de versão de rotas ====
//    @PostMapping("/v2")
//    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 personDTOV2){
//        return personService.createV2(personDTOV2);
//    }
//    //--------
    @PutMapping(produces = {"application/json","application/xml", "application/x-yaml"},
            consumes = {"application/json","application/xml", "application/x-yaml"})
    public PersonDTO update(@RequestBody PersonDTO personDTO){
        var person = personService.update(personDTO);
        return person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        personService.delete(id);
        return  ResponseEntity.ok().build();
    }
}
