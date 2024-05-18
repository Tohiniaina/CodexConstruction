package codex.evaluation.controllers;

//import codex.evaluation.security.RoleAllowed;
import codex.evaluation.security.ProfileCheck;
import codex.evaluation.service.UtilService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/codexconstruction")
public class MainController {
    @Autowired
    private UtilService utilService;

    @GetMapping()
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/client")
    public String client(Model model, HttpServletRequest request){
        /*boolean checksession = ProfileCheck.checkSession(request);
        if (checksession == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }*/

        HttpSession session = request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        return "Client/home";
    }

    @GetMapping("/admin")
    public String admin(Model model, HttpServletRequest request){
        boolean author = utilService.checkAdmin(request);
        if (author == false) {
            model.addAttribute("exception", "Access denied");
            return "exception";
        }

        HttpSession session = request.getSession();
        model.addAttribute("user", session.getAttribute("user"));
        return "Admin/home";
    }
}
