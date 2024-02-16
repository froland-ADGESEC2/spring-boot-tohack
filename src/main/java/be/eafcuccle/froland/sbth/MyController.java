package be.eafcuccle.froland.sbth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {
  @Autowired
  private EntityManager em;
  @Autowired
  private CommentRepository commentRepository;

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
    String jpqlQuery;
    if (filter == null || filter.isBlank()) {
      jpqlQuery = """
        SELECT b
        FROM Book b
        WHERE b.owner.id = %d
        """.formatted(employeeId);
    } else {
      jpqlQuery = """
        SELECT b
        FROM Book b
        WHERE b.owner.id = %d
        AND b.title LIKE '%%%s%%'
        """.formatted(employeeId, filter);
    }
    TypedQuery<Book> bookQuery = em.createQuery(jpqlQuery, Book.class);
    List<Book> books = bookQuery.getResultList();
    model.addAttribute("books", books);
    model.addAttribute("filter", filter);
    return "books";
  }

  @GetMapping("/comments")
  public String listComments(Model model) {
    List<Comment> allComments = commentRepository.findAll(Sort.by("creationTimestamp").descending());
    model.addAttribute("comments", allComments);
    return "comments";
  }

  @PostMapping("/comments")
  public String addComment(@RequestParam String content, HttpSession session) {
    Employee employee = (Employee) session.getAttribute("employee");
    Comment comment = new Comment(employee, content);
    commentRepository.save(comment);
    return "redirect:/comments";
  }
}
