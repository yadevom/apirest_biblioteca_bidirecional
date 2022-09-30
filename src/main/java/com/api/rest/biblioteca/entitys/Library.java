package com.api.rest.biblioteca.entitys;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    // Si se elimina biblioteca, se elimina tambien todos los libros
    // Una biblioteca esta relacionada con muchos libros (mappedBy indica que no es propietaria)
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    // Cada vez que estableciendo libros debo establecerle la biblioteca actual
    public void setBooks(Set<Book> books) {
        this.books = books;
        for(Book book : books) {
            book.setLibrary(this);
        }
    }
}
