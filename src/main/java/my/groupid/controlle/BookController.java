package my.groupid.controlle;

import my.groupid.account.Account;
import my.groupid.account.AccountRepository;
import my.groupid.entity.Book;
import my.groupid.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.DateTimeException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public String findAllBooks(Book book, Principal principal, Model model){
        model.addAttribute("books", bookRepository.findAll());
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }

    @PostMapping("/books")
    String saveBook(@ModelAttribute("book") Book book, Principal principal, Model model) {
        Assert.notNull(book);
        book.setDateCreated(Instant.now());
        bookRepository.save(book);
        model.addAttribute("books", bookRepository.findAll());
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }


}
