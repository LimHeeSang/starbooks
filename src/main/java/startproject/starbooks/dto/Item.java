package startproject.starbooks.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Item {

    private String title;	//책 제목
    private String author;  //저자명

    private String image;
    private String isbn;
    private Integer price;
    private LocalDate pubDate;
    private String publisher;

    private String description;

}
