package com.uldemy.controllers;

import com.uldemy.dto.v1.BooksDTO;
import com.uldemy.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books/v1")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping(produces = {"application/json","application/xml", "application/x-yaml"})
    public List<BooksDTO> findAll(){
        List<BooksDTO> booksDTOS = booksService.findAll();

        if (booksDTOS.isEmpty()){
            throw  new ResourceAccessException("Not Have items");
        }

        for (BooksDTO book: booksDTOS) {
            Long id = book.getId();
            book.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
        }

        return booksDTOS;
    }

    @GetMapping(value = "/{id}", produces = {"application/json","application/xml", "application/x-yaml"})
    public BooksDTO findById(@PathVariable Long id){
        return booksService.findById(id)
                .add(linkTo(methodOn(BooksController.class).findAll())
                        .withRel("List books"));
    }


    @PostMapping(produces = {"application/json","application/xml", "application/x-yaml"},
            consumes = {"application/json","application/xml", "application/x-yaml"})
    public BooksDTO create(@RequestBody BooksDTO booksDTO){
        var book = booksService.create(booksDTO);
        return book.add(linkTo(methodOn(BooksController.class).findById(book.getId())).withSelfRel());

    }

    @PutMapping(produces = {"application/json","application/xml", "application/x-yaml"},
            consumes = {"application/json","application/xml", "application/x-yaml"})
    public BooksDTO update(@RequestBody BooksDTO booksDTO){
        var book = booksService.update(booksDTO);
        return book.add(linkTo(methodOn(BooksController.class).findById(book.getId())).withSelfRel());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        booksService.delete(id);
        return  ResponseEntity.ok().build();
    }
}
