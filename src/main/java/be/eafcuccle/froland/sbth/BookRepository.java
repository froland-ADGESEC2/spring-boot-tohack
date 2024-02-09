package be.eafcuccle.froland.sbth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
