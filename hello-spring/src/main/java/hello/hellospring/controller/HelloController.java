package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // "/hello"가 들어오면 이 메서드에 매핑해준다.
    public String hello(Model model){
        model.addAttribute("data", "hello!!(${data}에 들어갈 값)");
        return "hello";
    }
}
