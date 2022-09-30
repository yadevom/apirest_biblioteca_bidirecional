package com.api.rest.biblioteca.entitys;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
// Regla nombre del libro debe ser unico
@Table(name = "books", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    // carga perezosa, trae el dato cuando se necesita
    // Muchos libros pueden pertecener a una biblioteca
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    // Union de columna
    @JoinColumn(name = "library_id")
    // Evitar error LAZY serealizacion, intentar cargar objeto en memoria pero sesion esta cerrada
    // Con esto se ignora la propiedad inicialization exception
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Library library;

}