package hello.antproject.domain.book;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class MemoryBookRepository implements BookRepository {

    private final Map<Long, Book> store=new HashMap<>();
    private long sequence=0L;

    @Override
    public Book save(Book book) {
        book.setId(++sequence);
        store.put(book.getId(), book);
        return book;
    }

    @Override
    public Book findById(Long bookId) {
        Book book = store.get(bookId);
        return book;
    }

    @Override
    public List<Book> findAll() {
            return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long id, Book updateBook) {
        Book previousBook = store.get(id);
        previousBook.setAuthor(updateBook.getAuthor());
        previousBook.setTitle(updateBook.getTitle());
    }

    @Override
    public void remove(Long id) {
        store.remove(id);
    }
}
