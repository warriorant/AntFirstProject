package hello.antproject.domain;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private BookStatus status;
    private String author;


    public Book(){
        this.status=BookStatus.AVAILABLE;
    }
    public Book(String title,String author){
        this.title=title;
        this.author=author;
        this.status= BookStatus.AVAILABLE;
    }



}
