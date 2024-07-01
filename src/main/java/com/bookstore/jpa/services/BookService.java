package com.bookstore.jpa.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.jpa.dtos.BookRecordDTO;
import com.bookstore.jpa.models.BookModel;
import com.bookstore.jpa.models.ReviewModel;
import com.bookstore.jpa.repositories.AuthorRepository;
import com.bookstore.jpa.repositories.BookRepository;
import com.bookstore.jpa.repositories.PublisherRepository;


@Service
public class BookService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final PublisherRepository publisherRepository;

  public BookService(
      BookRepository bookRepository,
      AuthorRepository authorRepository,
      PublisherRepository publisherRepository
    ) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
    this.publisherRepository = publisherRepository;
  }

  public List<BookModel> getAllBooks() {
    return bookRepository.findAll();
  }

  @Transactional // Evitar conflitos de transaçãos; Caso uma operação falhe, todas as operações devem ser desfeitas(ROLLBACK) preservando os dados;
  public BookModel saveBook(BookRecordDTO bookRecordDTO) {
    BookModel book = new BookModel();
    book.setTitle(bookRecordDTO.title());
    book.setPublisher(publisherRepository.findById(bookRecordDTO.publisherId()).get());
    book.setAuthors(authorRepository.findAllById(bookRecordDTO.authorId()).stream().collect(Collectors.toSet()));

    ReviewModel reviewModel = new ReviewModel();
    reviewModel.setComment(bookRecordDTO.reviewComment());
    reviewModel.setBook(book);
    book.setReview(reviewModel);

    return bookRepository.save(book);
  }

  @Transactional
    public void deleteBook(UUID id){
        bookRepository.deleteById(id);
    }
}
