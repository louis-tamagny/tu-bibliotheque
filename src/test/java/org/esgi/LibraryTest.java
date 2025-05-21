package org.esgi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
  public Library library;

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @BeforeEach
  public void setUp() {
    library = new Library();
    Book firstBook = new Book("The Lord of the Rings", "J. R. R. Tolkien", 1954);
    library.addBook(firstBook);
  }

  @Test
  public void testInitLibrary() {
    Library emptyLibrary = new Library();
    assertEquals(0, emptyLibrary.getBooks().size());
  }

  @Test
  public void testAddBook() {
    Book book = new Book("1984", "George Orwell", 1949);
    library.addBook(book);

    assertEquals(2, library.getBooks().size());
  }

  @Test
  public void testRemoveBook() {
    Book book = new Book("1984", "George Orwell", 1949);
    library.addBook(book);
    library.removeBook("1984");

    assertEquals(1, library.getBooks().size());

    library.removeBook("The Lord of the Rings");

    assertEquals(0, library.getBooks().size());

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      library.removeBook("1984");
    });
    assertEquals("Book not found", exception.getMessage());
  }

  @Test
  public void testFindBook() {
    Book book = new Book("1984", "George Orwell", 1949);
    library.addBook(book);

    Book foundBook = library.findBook("1984");
    assertEquals(book, foundBook);

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      library.findBook("The Catcher in the Rye");
    });
    assertEquals("Book not found", exception.getMessage());
  }

  @Test
  public void testGetBooks() {
    Book book = new Book("1984", "George Orwell", 1949);
    library.addBook(book);

    assertEquals(2, library.getBooks().size());

    ArrayList<Book> expectedList = new ArrayList<Book>();
    expectedList.add(new Book("The Lord of the Rings", "J. R. R. Tolkien", 1954));
    expectedList.add(book);

    assertEquals(expectedList, library.getBooks());
  }

  @Test
  public void displayBooks() {
    System.setOut(new PrintStream(outputStream));
    library.displayBooks();

    String expectedOutput = "The Lord of the Rings by J. R. R. Tolkien (1954)\n";
    assertEquals(expectedOutput, outputStream.toString());

    System.setOut(originalOut);
  }
}
