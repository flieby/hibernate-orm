package my.groupid.home;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import my.groupid.entity.Book;
import my.groupid.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {

	@ModelAttribute("module")
	String module() {
		return "home";
	}

	@Autowired
	private BookService bookService;

	@GetMapping("/")
	String index(Principal principal, Model model) {
		model.addAttribute("springVersion", SpringVersion.getVersion());
		List<Book> books = bookService.getAllBooks();
		Book book = new Book();
		books.add(book);
		if(books==null || books.isEmpty())
			books = new ArrayList<>();
		model.addAttribute("books", books);
		model.addAttribute("book", book);
		return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
	}
}
