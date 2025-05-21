package org.esgi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class LibraryFunctionalTest {
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @Test
  public void testFunctionalLibrary() {
    System.setOut(new PrintStream(outputStream));
    
    Library library = new Library();
    assertEquals(0, library.getBooks().size());

    library.addBook(new Book("1984", "George Orwell", 1949));
    library.addBook(new Book("Dune", "Frank Herbert", 1965));

    assertEquals(2, library.getBooks().size());

    library.removeBook("1984");
    assertEquals(1, library.getBooks().size());

    library.displayBooks();
    String expectedOutput = "Dune by Frank Herbert (1965)\n";
    assertEquals(expectedOutput, outputStream.toString());

    System.setOut(originalOut);
  }
}
