package com.uldemy.controllers;

import com.uldemy.dto.PersonDTO;
import com.uldemy.dto.V2.PersonDTOV2;
import com.uldemy.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id){
        return personService.findById(id);
    }


    @PostMapping
    public PersonDTO create(@RequestBody PersonDTO personDTO){
        return personService.create(personDTO);
    }

    //Simulando um troca de versão de rotas ====
    @PostMapping("/v2")
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 personDTOV2){
        return personService.createV2(personDTOV2);
    }
    //--------
    @PutMapping
    public PersonDTO update(@RequestBody PersonDTO personDTO){
        return personService.update(personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        personService.delete(id);
        return  ResponseEntity.ok().build();
    }
}
