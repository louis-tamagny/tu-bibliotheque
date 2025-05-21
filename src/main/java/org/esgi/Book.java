package org.esgi;

public class Book {
  private String title;
  private String author;
  private int publishedYear;

  public Book(String title, String author, int publishedYear){
    this.title = title;
    this.author = author;
    this.publishedYear = publishedYear;
  }

  public String getTitle(){
    return this.title;
  }

  public String getAuthor(){
    return this.author;
  }

  public int getPublishedYear(){
    return this.publishedYear;
  }

  public void display(){
    System.out.println(this.title + " by " + this.author + " (" + this.publishedYear + ")");
  }

  public boolean equals(Object o){
    if(o instanceof Book){
      Book book = (Book) o;
      return this.title.equals(book.getTitle()) && this.author.equals(book.getAuthor()) && this.publishedYear == book.getPublishedYear();
    }
    return false;
  }
}
