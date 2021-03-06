package com.uldemy.dto.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BooksDTO extends RepresentationModel<BooksDTO> implements Serializable {

    private Long id;
    private String author;
    @NotEmpty(message = "This date is necessary")
    @Schema(description = "this is book's date", example = "2022-05-01T21:07:48.657Z")
    private Date launchDate;
    private Double price;
    private String title;

    public BooksDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BooksDTO booksDTO = (BooksDTO) o;
        return Objects.equals(id, booksDTO.id) && Objects.equals(author, booksDTO.author) && Objects.equals(launchDate, booksDTO.launchDate) && Objects.equals(price, booksDTO.price) && Objects.equals(title, booksDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, author, launchDate, price, title);
    }
}
