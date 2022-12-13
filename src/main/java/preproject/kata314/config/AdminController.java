package preproject.kata314.config;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public ModelAndView getAdminPage() {
        return new ModelAndView("adminPage");
    }

    @GetMapping("/user")
    public ModelAndView getUserPage() {
        return new ModelAndView("userPage");
    }
}
