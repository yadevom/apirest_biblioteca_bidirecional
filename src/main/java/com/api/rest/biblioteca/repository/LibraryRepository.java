package com.api.rest.biblioteca.repository;

import com.api.rest.biblioteca.entitys.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Long> {
}