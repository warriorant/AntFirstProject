package hello.antproject.service;

import hello.antproject.domain.Book;
import hello.antproject.domain.BookRepository;
import hello.antproject.domain.BookStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//서비스에서 레포지토리 쓰려면 의존성 주입해줘야하는데 객체만 부른다고 되는게 아님 즉 생성자로 의존성 주입해주는게 좋은데 직접 하든가 아님 RequiredArgsConstructor쓰셈
@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

//    public BookService(BookRepository bookRepository){
//        this.bookRepository=bookRepository;
//
//    }
    public Book add(Book book){
        Book savedBook = bookRepository.save(book);
        return savedBook;
    }
    public Book findOne(Long bookId){
        Book foundBook = bookRepository.findById(bookId);
        return foundBook;
    }
    public List<Book> findAll(){
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }
    public void updateBook(Long id,Book newbook){
        bookRepository.update(id,newbook);
    }

    public void removeBook(Long id){
        bookRepository.remove(id);
    }

    public void borrowed(Long id){
        Book borrowedBook = bookRepository.findById(id);
        borrowedBook.setStatus(BookStatus.BORROWED);
        bookRepository.update(id,borrowedBook);
    }
    public void returnBook(Long id){
        Book returnedBook = bookRepository.findById(id);
        returnedBook.setStatus(BookStatus.AVAILABLE);
        bookRepository.update(id,returnedBook);
    }



}
