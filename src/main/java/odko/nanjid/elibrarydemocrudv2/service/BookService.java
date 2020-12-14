package odko.nanjid.elibrarydemocrudv2.service;

import odko.nanjid.elibrarydemocrudv2.model.Book;

import java.util.List;

public interface BookService {

    public abstract List<Book> getAllBooks();
    public abstract Book saveBook(Book book);
    public abstract Book getBookById(Integer bookId);
    public abstract void deleteBookById(Integer bookId);
    public abstract List<Book> searchBooks(String searchString);

}
