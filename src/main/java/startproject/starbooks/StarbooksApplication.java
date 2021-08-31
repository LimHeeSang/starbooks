package startproject.starbooks;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import startproject.starbooks.domain.dto.BooksResponseDto;
import startproject.starbooks.domain.dto.Item;
import startproject.starbooks.service.BookApiClient;
import startproject.starbooks.service.BookInsert;

@SpringBootApplication
@RequiredArgsConstructor
public class StarbooksApplication {


    public static void main(String[] args) {
        SpringApplication.run(StarbooksApplication.class, args);

    }



}
