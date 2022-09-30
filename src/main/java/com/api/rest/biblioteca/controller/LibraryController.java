package com.api.rest.biblioteca.controller;

import com.api.rest.biblioteca.entitys.Library;
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
@RequestMapping("/api/library")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;

    // Uso paginacion
    @GetMapping
    public ResponseEntity<Page<Library>> listAllLibraries(Pageable pageable) {
        return ResponseEntity.ok(libraryRepository.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Library> saveLibrary(@Valid @RequestBody Library library) {
        Library libraryWasSave = libraryRepository.save(library);
        // Del objeto creamos una nueva URI con el ID actual
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(libraryWasSave.getId()).toUri();

        // Retornamos entidad o rta con objetos
        return ResponseEntity.created(location).body(libraryWasSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Library> editLibrary(@PathVariable Long id, @Valid @RequestBody Library library) {
        Optional<Library> libraryOptional = libraryRepository.findById(id);

        // Si no esta presente retornar que no se ha podido procesar
        if(!libraryOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        library.setId(libraryOptional.get().getId());
        libraryRepository.save(library);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Library> deleteLibrary(@PathVariable Long id) {
        Optional<Library> libraryOptional = libraryRepository.findById(id);

        if(!libraryOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        libraryRepository.delete(libraryOptional.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable Long id) {
        Optional<Library> libraryOptional = libraryRepository.findById(id);

        if(!libraryOptional.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(libraryOptional.get());
    }

}