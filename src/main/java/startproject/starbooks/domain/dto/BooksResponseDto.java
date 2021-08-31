package startproject.starbooks.domain.dto;

import lombok.Data;

@Data
public class BooksResponseDto {

    private int display;
    private Item[] items;

}
