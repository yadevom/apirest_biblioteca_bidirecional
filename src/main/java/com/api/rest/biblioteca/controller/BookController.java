package com.api.rest.biblioteca.controller;

import com.api.rest.biblioteca.entitys.Book;
import com.api.rest.biblioteca.entitys.Library;
import com.api.rest.biblioteca.repository.BookRepository;
import com.api.rest.biblioteca.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @GetMapping
    public ResponseEntity<Page<Book>> listBooks(Pageable pageable) {
        return ResponseEntity.ok(bookRepository.findAll(pageable));
    }

    // Obtenemos 1ero el ID de la biblioteca con que esta relacionado el libro
    // Ya que muchos libros estan relacionados a una biblioteca
    @PostMapping
    public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) {
        Optional<Library> libraryOptional = libraryRepository.findById(book.getLibrary().getId());

        if(!libraryOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        book.setLibrary(libraryOptional.get());
        Book bookWasSave = bookRepository.save(book);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bookWasSave.getId()).toUri();

        return ResponseEntity.created(location).body(bookWasSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book, @PathVariable Long id) {
        Optional<Library> libraryOptional = libraryRepository.findById(book.getLibrary().getId());

        if(!libraryOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Optional<Book> bookOptional = bookRepository.findById(id);

        if(!bookOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        book.setLibrary(libraryOptional.get());
        book.setId(bookOptional.get().getId());
        bookRepository.save(book);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if(!bookOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        bookRepository.delete(bookOptional.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> searchBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if(!bookOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(bookOptional.get());
    }
}
