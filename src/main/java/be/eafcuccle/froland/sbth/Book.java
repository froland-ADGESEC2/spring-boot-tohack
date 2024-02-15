package be.eafcuccle.froland.sbth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Employee owner;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "author", nullable = false)
  private String author;

  public Book(String author, String title, Employee owner) {
    this.author = author;
    this.title = title;
    this.owner = owner;
  }

  protected Book() {
  }

  public Employee getOwner() {
    return owner;
  }

  public void setOwner(Employee owner) {
    this.owner = owner;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

}
