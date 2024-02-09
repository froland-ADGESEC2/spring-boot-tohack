package be.eafcuccle.froland.sbth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
    Employee user2 = employeeRepository.save(new Employee("user2", "user2"));
    Employee user3 = employeeRepository.save(new Employee("user3", "user3"));
  }

}
