package be.eafcuccle.froland.sbth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataPopulator implements CommandLineRunner {
  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private BookRepository bookRepository;

  @Override
  public void run(String... args) throws Exception {
    bookRepository.deleteAll();
    employeeRepository.deleteAll();

    Employee admin = employeeRepository.save(new Employee("admin", "admin123"));
    Employee user1 = employeeRepository.save(new Employee("user1", "user1"));
    employeeRepository.save(new Employee("user2", "user2"));
    employeeRepository.save(new Employee("user3", "user3"));

    bookRepository.saveAll(List.of(
      new Book("Joshua Bloch", "Effective Java", admin),
      new Book("Robert C. Martin", "Clean Code", admin),
      new Book("Kathy Sierra & Bert Bates", "Head First Java", admin),
      new Book("Martin Fowler", "Refactoring", user1),
      new Book("Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", "Design Patterns", user1),
      new Book("Brian Goetz", "Java Concurrency in Practice", user1)
    ));
  }

}
