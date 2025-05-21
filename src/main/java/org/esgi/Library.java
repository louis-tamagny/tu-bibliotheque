package org.esgi;

import java.util.ArrayList;

public class Library {
  private ArrayList<Book> books;

  public Library() {
    this.books = new ArrayList<Book>();
  }

  public void addBook(Book newBook) {
    this.books.add(newBook);
  }

  public void removeBook(String title) throws IllegalArgumentException {
    if (!this.books.removeIf(book -> book.getTitle().equals(title))) {
      throw new IllegalArgumentException("Book not found");
    }
  }

  public Book findBook(String title) throws IllegalArgumentException {
    for (Book book : this.books) {
      if (book.getTitle().equals(title)) {
        return book;
      }
    }
    throw new IllegalArgumentException("Book not found");
  }

  public ArrayList<Book> getBooks() {
    return books;
  }

  public void displayBooks() {
    for (Book book : this.books) {
      book.display();
    }
  }
}
