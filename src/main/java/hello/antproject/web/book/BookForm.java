package hello.antproject.web.book;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookForm {
    private Long id;

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @Min(0)
    private int quantity;


}
