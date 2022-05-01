package com.uldemy.services;

import com.uldemy.converter.DozerConverter;
import com.uldemy.dto.v1.BooksDTO;
import com.uldemy.exceptions.ResourceNotFoundException;
import com.uldemy.model.Books;
import com.uldemy.repositories.BooksRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    public void delete(Long id){
        var book = booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        booksRepository.delete(book);

    }

    public BooksDTO create(BooksDTO booksDTO){
        var bookEntity = DozerConverter.parseObject(booksDTO, Books.class);

        return DozerConverter.parseObject(booksRepository.save(bookEntity), BooksDTO.class);
    }

    public BooksDTO update(BooksDTO booksDTO){
        var entity = booksRepository.findById(booksDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        BeanUtils.copyProperties(booksDTO, entity);

        return DozerConverter.parseObject(booksRepository.save(entity), BooksDTO.class);
    }
    public BooksDTO findById(Long id){
        var book = booksRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        return DozerConverter.parseObject(book, BooksDTO.class);
    }

    public List<BooksDTO> findAll(){
        return DozerConverter.parseListObjects(booksRepository.findAll(), BooksDTO.class);
    }
}
