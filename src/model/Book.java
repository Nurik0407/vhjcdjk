package model;


import ClassException.UniqueConstraintException;
import enums.Genre;
import enums.Language;
import service.BookService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Book implements BookService {
    private Long id;
    private String name;
    private Genre genre;
    private BigDecimal price;
    private String author;
    private Language language;
    private LocalDate publishedYear;

    private List<Book> bookDateBase = new ArrayList<>();

    public Book(Long id, String name, Genre genre, BigDecimal price, String author, Language language, LocalDate publishedYear) throws UniqueConstraintException {
        infoID();
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.author = author;
        this.language = language;
        this.publishedYear = publishedYear;
    }

    private void infoID() throws UniqueConstraintException {
        for (Book book : bookDateBase) {
          if (book.getId() == id){
              throw new UniqueConstraintException();
          }else {
              this.id=id;
          }
        }
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LocalDate getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(LocalDate publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public List<Book> createBooks(List<Book> books) {
        bookDateBase.addAll(books);
        return books;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDateBase;
    }

    @Override
    public List<Book> getBooksByGenre(String genre) {

        return bookDateBase.stream().filter(s -> s.getGenre().equals(Genre.valueOf(genre))).toList();
    }

    @Override
    public Book removeBookById(Long id) {

        for (Book book : bookDateBase) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> sortBooksByPriceInDescendingOrder() {
        Comparator<Book> comparator = new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        };

        return bookDateBase.stream().sorted(comparator).toList();
    }

    @Override
    public List<Book> filterBooksByPublishedYear() {

     return    bookDateBase.stream().filter(s -> s.getPublishedYear().getYear() <= LocalDate.now().getYear()
             && s.getPublishedYear().getYear() > LocalDate.now().minusYears(10).getYear()).toList();
    }

    @Override
    public List<Book> getBookByInitialLetter() throws Exception {
        System.out.println("Enter bukva book");
        String bukva = new Scanner(System.in).nextLine();
        if (bukva.length() != 1) {
            throw new Exception("Odna bukva!!");
        }

        return bookDateBase.stream().filter(s -> s.getName().startsWith(bukva)).toList();
    }

    @Override
    public Book maxPriceBook() {
        Comparator<Book> bookComparator = new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        };

        return bookDateBase.stream().max(bookComparator).get();
    }

    @Override
    public String toString() {
        return " \n       Book" +
                "\nid=" + id +
                "\nname='" + name +
                "\ngenre=" + genre +
                "\nprice=" + price +
                "\nauthor='" + author +
                "\nlanguage=" + language +
                "\npublishedYear=" + publishedYear +
                "\nbookDateBase=" + bookDateBase ;
    }
}
