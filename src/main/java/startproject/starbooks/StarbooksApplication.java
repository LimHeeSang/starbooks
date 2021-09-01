package startproject.starbooks;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class StarbooksApplication {


    public static void main(String[] args) {
        SpringApplication.run(StarbooksApplication.class, args);

    }
}
