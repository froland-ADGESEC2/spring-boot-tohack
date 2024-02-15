package be.eafcuccle.froland.sbth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {
  @Autowired
  private EntityManager em;

  @GetMapping("/")
  public String homePage(HttpSession session, Model model) {
    if (session.getAttribute("employee") == null) {
      return "redirect:/login";
    } else {
      model.addAttribute("employee", session.getAttribute("employee"));
      return "index";
    }
  }

  @GetMapping("/login")
  public String loginForm() {
    return "login";
  }

  @GetMapping("/failure")
  public String failure() {
    return "failure";
  }

  @PostMapping("/login")
  public String checkLogin(@RequestParam String login, @RequestParam String password, Model model, HttpSession session, HttpServletResponse response) {
    session.removeAttribute("employee");
    TypedQuery<Employee> q = em.createQuery(
        "SELECT e FROM Employee e WHERE e.login='" + login + "' AND e.password='" + password + "'", Employee.class);
    List<Employee> foundEmployees = q.getResultList();
    if (foundEmployees.isEmpty()) {
      return "redirect:/failure";
    } else {
      Employee foundEmployee = foundEmployees.get(0);
      session.setAttribute("employee", foundEmployee);
      model.addAttribute("employee", foundEmployee);
      response.addCookie(new Cookie("secret-cookie", "my-very-secret-cookie"));
      return "success";
    }
  }

  @GetMapping("/books/{employeeId}")
  public String books(Model model, @PathVariable Integer employeeId, @RequestParam(required = false) String filter) {
    Query employeeQuery = em.createQuery("SELECT e FROM Employee e WHERE e.id=" + employeeId);
    Employee employee = (Employee) employeeQuery.getSingleResult();
    model.addAttribute("employee", employee);
    TypedQuery<Book> bookQuery = em.createQuery("SELECT b FROM Book b WHERE b.owner.id=" + employeeId, Book.class);
    List<Book> books = bookQuery.getResultList();
    model.addAttribute("books", books);
    model.addAttribute("filter", filter);
    return "books";
  }
}
