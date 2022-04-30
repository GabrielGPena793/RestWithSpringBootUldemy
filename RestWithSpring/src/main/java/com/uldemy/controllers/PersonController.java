package com.uldemy.controllers;

import com.uldemy.dto.V2.PersonDTOV2;
import com.uldemy.dto.v1.PersonDTO;
import com.uldemy.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = {"application/json","application/xml", "application/x-yaml"})
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

    @GetMapping(value = "/{id}", produces = {"application/json","application/xml", "application/x-yaml"})
    public PersonDTO findById(@PathVariable Long id){
        return personService.findById(id);
    }


    @PostMapping(produces = {"application/json","application/xml", "application/x-yaml"},
            consumes = {"application/json","application/xml", "application/x-yaml"})
    public PersonDTO create(@RequestBody PersonDTO personDTO){
        return personService.create(personDTO);
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
        return personService.update(personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        personService.delete(id);
        return  ResponseEntity.ok().build();
    }
}
