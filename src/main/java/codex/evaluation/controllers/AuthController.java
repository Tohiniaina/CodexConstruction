package codex.evaluation.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import codex.evaluation.model.*;
import codex.evaluation.repository.UserAdminRepository;
import codex.evaluation.security.Encryptor;
import codex.evaluation.service.UserAdminService;
import codex.evaluation.service.UserClientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/codexconstruction/auth")
public class AuthController {
  @Autowired
  private UserClientService userClientService;

  @Autowired
  private UserAdminService userAdminService;

  @Autowired
  UserAdminRepository userAdminRepository;

  Encryptor encryptor;

  @GetMapping("loginadmin")
  public ModelAndView LoginAdmin(){
    ModelAndView modelAndView = new ModelAndView("LoginAdmin");
    return modelAndView;
  }

  @GetMapping("loginclient")
  public ModelAndView LoginClient(){
    ModelAndView modelAndView = new ModelAndView("LoginClient");
    return modelAndView;
  }

  @GetMapping("/inscriAdmin")
  public String inscri(Model model){
    return "inscri";
  }

  @PostMapping("/signinClient")
  public String authenticateUserClient(HttpServletRequest request, Model model, @RequestParam("numero") String numero) {
    Optional<UserClient> user = userClientService.findByNumero(numero);
    UserClient userClient = new UserClient();
    try {
      if (user.isEmpty()) {
        userClient = userClientService.save(new UserClient(numero));
      } else {
        userClient = user.get();
      }
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      return "LoginClient";
    }

    HttpSession session = request.getSession();
    session.setAttribute("user", userClient);
    return "redirect:/codexconstruction/client";
  }

  @PostMapping("/signinAdmin")
  public String authenticateUserAdmin(HttpServletRequest request, Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
    Optional<UserAdmin> user = userAdminService.findByEmailAndPassword(email, encryptor.encryptPassword(password));

    try {
      if (user.isEmpty()) {
        throw new Exception("Email or Password invalid");
      }
    } catch (Exception e) {
      model.addAttribute("error", e.getMessage());
      model.addAttribute("username", email);
      return "LoginAdmin";
    }
    HttpSession session = request.getSession();
    session.setAttribute("user", user.get());
    return "redirect:/codexconstruction/admin";
  }

  @PostMapping("/signupAdmin")
  public String registerUser(Model model, @RequestParam("nom") String nom, @RequestParam("email") String email, @RequestParam("password") String password) {
    try {
      if (userAdminRepository.existsByUsername(nom)) {
        throw new Exception("Error: Username is already taken!");
      }

      if (userAdminRepository.existsByEmail(email)) {
        throw new Exception("Error: Email is already in use!");
      }

      UserAdmin user = new UserAdmin(nom,
              email,
              encryptor.encryptPassword(password)
              );
      userAdminRepository.save(user);
    } catch (Exception e) {
        model.addAttribute("error", e.getMessage());
        return "inscri";
    }
    return "LoginAdmin";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }
    return "redirect:/codexconstruction";
  }
}
