package be.eafcuccle.froland.sbth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataPopulator implements CommandLineRunner {
  @Autowired
  private EmployeeRepository employeeRepository;

  @Override
  public void run(String... args) throws Exception {
    employeeRepository.save(new Employee("admin", "admin123"));
    employeeRepository.save(new Employee("user1", "user1"));
    employeeRepository.save(new Employee("user2", "user2"));
    employeeRepository.save(new Employee("user3", "user3"));
  }

}
