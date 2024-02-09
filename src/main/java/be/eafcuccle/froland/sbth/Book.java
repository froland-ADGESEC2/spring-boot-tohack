package be.eafcuccle.froland.sbth;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
  @Id
  @Column(name = "id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "author", nullable = false)
  private String author;

  @Column(name = "title", nullable = false)
  private String title;

  @ManyToOne(optional = false)
  @JoinColumn(name = "owner_id", nullable = false)
  private Employee owner;

  public Book(String author, String title, Employee owner) {
    this.author = author;
    this.title = title;
    this.owner = owner;
  }

  protected Book() {
  }

  public Integer getId() {
    return id;
  }

  public String getAuthor() {
    return author;
  }

  public String getTitle() {
    return title;
  }

  public Employee getOwner() {
    return owner;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setOwner(Employee owner) {
    this.owner = owner;
  }
}
