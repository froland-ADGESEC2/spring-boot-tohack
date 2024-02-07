package be.eafcuccle.froland.sbth;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Controller
public class MyController {
  @Autowired
  private EntityManager em;

  @GetMapping("/")
  public String showLoginForm() {
    return "index";
  }

  @PostMapping("/login")
  public String checkLogin(@RequestParam String login, @RequestParam String password, Model model) {
    Query q = em.createQuery(
        "SELECT e FROM Employee e WHERE e.login='" + login + "' AND e.password='" + password + "'");
    List<Employee> foundEmployees = q.getResultList();
    if (foundEmployees.isEmpty()) {
      model.addAttribute("msg", "Login failed");
      return "index";
    } else {
      model.addAttribute("employee", foundEmployees.get(0));
      return "success";
    }
  }
}