package odko.nanjid.elibrarydemocrudv2.repository;

import odko.nanjid.elibrarydemocrudv2.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    //Search queries

    List<Book> findAllByIsbnContainingOrTitleContainingOrPublisherContainingOrderByTitle(String isbn,
                                                                                         String title,
                                                                                         String pub);
    List<Book> findAllByOverdueFeeEquals(Double overdueFee);
    List<Book> findAllByDatePublishedEquals(LocalDate datePublished);

    //More queries
    List<Book> findBooksByDatePublishedIsStartingWith(String str);

}
