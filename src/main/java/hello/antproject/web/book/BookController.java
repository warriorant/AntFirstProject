package hello.antproject.web.book;


import hello.antproject.domain.book.Book;
import hello.antproject.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {

      private final BookService bookService;
//    public BookController(BookService bookService){
//        this.bookService=bookService;
//    }

    @GetMapping//전체 목록 출력
    public String books(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books",books);
        return "books/books";
    }

    @GetMapping("/{bookId}")//상세 정보 출력
    public String book(@PathVariable Long bookId,Model model){
        Book book = bookService.findOne(bookId);
        model.addAttribute("book",book);
        return "books/book";
    }

    @GetMapping("/add")//add Form뷰 화면에 띄우기
    public String addForm(@ModelAttribute("book") BookForm form){
        //이건 빈 객체 넣어줘야지 form에서 th:object="${book}" 사용가능해서 넣어주는 것
        return "books/addForm";
    }

    @PostMapping("/add")//addForm에서 받은 정보로 책 등록하기
    public String addBook(@Validated @ModelAttribute("book") BookForm form, RedirectAttributes redirectAttributes, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "books/addForm";
        }
        Book book=new Book(form.getTitle(),form.getAuthor(),form.getQuantity());
        Book savedBook = bookService.add(book);
        redirectAttributes.addAttribute("bookId",savedBook.getId());
        redirectAttributes.addAttribute("status",true);
        return "redirect:/books/{bookId}";//이거 그냥 컨트롤러로 보내는거임 위에서 찾으면 있음 두번째 컨트롤러
    }

    @PostMapping("/{bookId}/delete")//책 삭제하기
    public String delete(@PathVariable Long bookId){
        bookService.removeBook(bookId);
        return "redirect:/books";
    }//삽입 삭제같은 데이터 변경 작업 후에는 무조건 redirect이다 redirect는 참고로 컨트롤러 호출이다

    @GetMapping("/{bookId}/edit") // 책 수정 폼 보내기
    public String editForm(@PathVariable Long bookId, Model model) {
        Book book = bookService.findOne(bookId);

        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setTitle(book.getTitle());
        form.setAuthor(book.getAuthor());
        form.setQuantity(book.getQuantity());

        model.addAttribute("book", form); // editForm.html의 th:object="${book}" 유지
        return "books/editForm";
    }

    @PostMapping("/{bookId}/edit")
    public String edit(@PathVariable Long bookId,
                       @Validated @ModelAttribute("book") BookForm form,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/editForm";
        }
        Book updateParam = new Book();
        updateParam.setTitle(form.getTitle());
        updateParam.setAuthor(form.getAuthor());
        updateParam.setQuantity(form.getQuantity());
        updateParam.syncStatus();
        bookService.updateBook(bookId, updateParam);
        return "redirect:/books/{bookId}";
    }

    @PostMapping("/{bookId}/borrow")
    public String borrow(@PathVariable Long bookId, RedirectAttributes redirectAttributes) {
        bookService.borrowed(bookId);
        redirectAttributes.addAttribute("bookId", bookId);
        return "redirect:/books/{bookId}";
    }

    @PostMapping("/{bookId}/return")
    public String returnBook(@PathVariable Long bookId, RedirectAttributes redirectAttributes) {
        bookService.returnBook(bookId);
        redirectAttributes.addAttribute("bookId", bookId);
        return "redirect:/books/{bookId}";
    }


}
