package startproject.starbooks.domain.dto;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
