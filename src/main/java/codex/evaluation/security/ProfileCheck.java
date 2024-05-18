package codex.evaluation.security;

import codex.evaluation.model.UserAdmin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ProfileCheck {
    /*public static boolean checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        System.out.println("Mandeha tsara");
        if (session != null && session.getAttribute("user") != null) {
            return true;
        }
        return false;
    }
    public static boolean checkProfil(HttpServletRequest request, String role) {
        HttpSession session = request.getSession(false);
        System.out.println("Mandeha tsara");
        if (session != null && session.getAttribute("user") != null) {
            UserAdmin user = (UserAdmin) session.getAttribute("user");
            if (role.equals(user.getRole().getName().toString())) {
                return true;
            }
        }
        return false;
    }*/
}
