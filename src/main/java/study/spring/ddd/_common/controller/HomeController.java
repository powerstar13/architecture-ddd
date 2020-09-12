package study.spring.ddd._common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    /**
     * `JSP`를 사용하여 View 출력
     */
    @GetMapping(value = "/", name = "index")
    public ModelAndView index()
    {
        return new ModelAndView("index");
    }

    /**
     * `Thymeleaf`를 사용하여 View 출력
     */
    @GetMapping(value = "/welcome", name = "welcome")
    public ModelAndView welcome()
    {
        return new ModelAndView("thymeleaf/welcome");
    }

}
