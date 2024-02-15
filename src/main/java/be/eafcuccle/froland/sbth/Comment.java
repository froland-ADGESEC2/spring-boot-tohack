package be.eafcuccle.froland.sbth;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "creation_timestamp", nullable = false)
  private LocalDateTime creationTimestamp;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "author_id")
  private Employee author;

  @Column(name = "content")
  private String content;

  protected Comment() {}

  public Comment(Employee author, String content) {
    this.author = author;
    this.content = content;
    creationTimestamp = LocalDateTime.now();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDateTime getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(LocalDateTime creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }

  public Employee getAuthor() {
    return author;
  }

  public void setAuthor(Employee author) {
    this.author = author;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
