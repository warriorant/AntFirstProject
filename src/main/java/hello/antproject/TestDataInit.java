package hello.antproject;


import hello.antproject.domain.Book;
import hello.antproject.service.BookService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final BookService bookService;

    @PostConstruct
    public void init(){
        bookService.add(new Book("bookA","kim"));
        bookService.add(new Book("bookB","jang"));
    }
}
