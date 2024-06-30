package com.bookstore.jpa.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_book")
public class BookModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, unique = true)
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "publisher_id")
  private PublisherModel publisher;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable( // cria uma tabela auxiliar para unir a tabela 'tb_book' e 'tb_author' para relacionar o Autor e o Livro;
    name = "tb_book_author",
    joinColumns = @JoinColumn(name = "book_id"), // referência da tabela 'tb_book' na coluna 'book_id' que é a chave estrangeira;
    inverseJoinColumns = @JoinColumn(name = "author_id") // referência da tabela 'tb_author' na coluna 'author_id' que é a chave primaria;
  )
  private Set<AuthorModel> authors = new HashSet<>(); // Um Livro tem uma colecção de Autores;

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public PublisherModel getPublisher() {
    return publisher;
  }

  public void setPublisher(PublisherModel publisher) {
    this.publisher = publisher;
  }

  public Set<AuthorModel> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<AuthorModel> authors) {
    this.authors = authors;
  }
}
