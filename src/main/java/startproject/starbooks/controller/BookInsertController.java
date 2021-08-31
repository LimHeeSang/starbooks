package startproject.starbooks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import startproject.starbooks.service.BookInsert;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BookInsertController {

    private final BookInsert bookInsert;

    //@RequestMapping("/home")
    @ResponseBody
    public String execute() {
        log.info("[BookInsertController.execute()]");
        bookInsert.insert();
        return "ok";
    }
}
