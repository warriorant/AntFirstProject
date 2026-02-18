package hello.antproject.domain.book;

import java.util.List;

public interface BookRepository {
    public Book save(Book book);
    public Book findById(Long bookId);
    public List<Book> findAll();
    public void update(Long id, Book updateBook);
    public void remove(Long id);

}
