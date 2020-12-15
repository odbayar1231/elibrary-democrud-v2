package odko.nanjid.elibrarydemocrudv2.controller;

import odko.nanjid.elibrarydemocrudv2.model.Book;
import odko.nanjid.elibrarydemocrudv2.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping(value = {"/elibrary/book/list", "/book/list"})
    public ModelAndView listBooks() {
        ModelAndView modelAndView = new ModelAndView();
        List<Book> books = bookService.getAllBooks();
        modelAndView.addObject("books", books);
        modelAndView.addObject("searchString", "");
        modelAndView.addObject("booksCount", books.size());
        modelAndView.setViewName("book/list");
        return modelAndView;
    }

    @GetMapping(value = {"/elibrary/book/new","/book/new"})
    public String displayNewBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/new";
    }

    @PostMapping(value = {"/elibrary/book/new", "/book/new"})
    public String addNewBook(@Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "book/new";
        }
        book = bookService.saveBook(book);
        return "redirect:/elibrary/book/list";
    }

    @GetMapping(value = {"/elibrary/book/edit/{bookId}", "/book/edit/{bookId}"})
    public String editBook(@PathVariable Integer bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        if(book != null){
            model.addAttribute("book", book);
            return "book/edit";
        }
        return "book/list";
    }

    @PostMapping(value = {"/elibrary/book/edit", "/book/edit"})
    public String updateBook(@Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "book/edit";
        }
        book = bookService.saveBook(book);
        return "redirect:/elibrary/book/list";
    }

    @GetMapping(value = {"/elibrary/book/delete/{bookId}","/book/delete/{bookId}"})
    public String deleteBook(@PathVariable Integer bookId, Model model) {
        bookService.deleteBookById(bookId);
        return "redirect:/elibrary/book/list";
    }


    @GetMapping(value = {"/elibrary/book/search", "/book/search"})
    public ModelAndView searchBooks(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Book> books = bookService.searchBooks(searchString);
        modelAndView.addObject("books", books);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("booksCount", books.size());
        modelAndView.setViewName("book/list");
        return modelAndView;
    }
}
