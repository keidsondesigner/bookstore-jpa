package com.bookstore.jpa.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_author")
public class AuthorModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String name;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Apenas escrita, para evitar problemas na serialização;
  @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY) // O dono do relacionamento, que é o authors, mapeando referência do atributo 'authors' no Relacionamento entre Autor e Books;
  private Set<BookModel> books = new HashSet<>(); // Um Autor tem uma colecção de Livros;

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  public UUID getId() {
    return id;
  }
  public void setId(UUID id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Set<BookModel> getBooks() {
    return books;
  }
  public void setBooks(Set<BookModel> books) {
    this.books = books;
  }
}
