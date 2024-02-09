package be.eafcuccle.froland.sbth;

import jakarta.persistence.*;

@Entity
@Table(name = "Employee", uniqueConstraints = {
  @UniqueConstraint(name = "uc_employee_login", columnNames = {"login"})
})
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String login;

  private String password;

  protected Employee() {}

  public Employee(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public long getId() {
    return id;
  }

  public String getLogin() {
    return login;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Employee other = (Employee) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", login=" + login + "]";
  }

}
