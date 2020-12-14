package odko.nanjid.elibrarydemocrudv2.service.impl;

import odko.nanjid.elibrarydemocrudv2.model.Book;
import odko.nanjid.elibrarydemocrudv2.repository.BookRepository;
import odko.nanjid.elibrarydemocrudv2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> getAllBooks(){
        return (List<Book>)repository.findAll();
    }

    @Override
    public Book saveBook(Book book) { return repository.save(book); }

    @Override
    public Book getBookById(Integer bookId) {
        return repository.findById(bookId).orElse(null);
    }

    @Override
    public void deleteBookById(Integer bookId) { repository.deleteById(bookId);}

    @Override
    public List<Book> searchBooks(String searchString) {
        
    }



}
