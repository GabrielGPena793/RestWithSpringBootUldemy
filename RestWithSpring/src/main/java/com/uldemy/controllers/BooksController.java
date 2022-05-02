package com.uldemy.controllers;

import com.uldemy.dto.v1.BooksDTO;
import com.uldemy.services.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books/v1")
@Tag(name = "Books", description = "Books endpoints")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping(produces = {"application/json","application/xml", "application/x-yaml"})
    @Operation(summary = "List all books", description = "Return all books in database", tags = "Books")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",  description = "everything is ok" ),
            @ApiResponse(responseCode = "404",  description = "Not Have items" )
    })
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

    @Operation(summary = "Find book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "ok" ),
            @ApiResponse(responseCode = "404",  description = "Not found items for this id" )
    })
    @GetMapping(value = "/{id}", produces = {"application/json","application/xml", "application/x-yaml"})
    public BooksDTO findById(@PathVariable Long id){
        return booksService.findById(id)
                .add(linkTo(methodOn(BooksController.class).findAll())
                        .withRel("List books"));
    }

    @Operation(summary = "Create data in book's database")
    @ApiResponse(responseCode = "200",  description = "ok" )
    @PostMapping(produces = {"application/json","application/xml", "application/x-yaml"},
            consumes = {"application/json","application/xml", "application/x-yaml"})
    public BooksDTO create(@RequestBody BooksDTO booksDTO){
        var book = booksService.create(booksDTO);
        return book.add(linkTo(methodOn(BooksController.class).findById(book.getId())).withSelfRel());

    }

    @Operation(summary = "Update data in book's database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",  description = "ok" ),
            @ApiResponse(responseCode = "404",  description = "Not found items for this id" )
    })
    @PutMapping(produces = {"application/json","application/xml", "application/x-yaml"},
            consumes = {"application/json","application/xml", "application/x-yaml"})
    public BooksDTO update(@RequestBody BooksDTO booksDTO){
        var book = booksService.update(booksDTO);
        return book.add(linkTo(methodOn(BooksController.class).findById(book.getId())).withSelfRel());
    }

    @Operation(summary = "Delete data in book's database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",  description = "delete successfully" ),
            @ApiResponse(responseCode = "404",  description = "Not found items for this id" )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        booksService.delete(id);
        return  ResponseEntity.ok().build();
    }
}
