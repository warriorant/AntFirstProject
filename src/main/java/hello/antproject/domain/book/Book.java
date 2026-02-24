package hello.antproject.domain.book;

import lombok.Data;

@Data
public class Book {
    private Long id;
    private String title;
    private BookStatus status;
    private String author;
    private int quantity;


    public Book(){
        syncStatus();
    }
    public Book(String title,String author,int quantity){
        this.title=title;
        this.author=author;
        this.quantity=quantity;
        syncStatus();
    }
    public void syncStatus(){
        if(this.quantity>0){
            this.status=BookStatus.AVAILABLE;
        }
        else{
            this.status=BookStatus.BORROWED;
        }
    }



}
