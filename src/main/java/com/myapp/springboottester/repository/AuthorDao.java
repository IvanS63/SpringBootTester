package com.myapp.springboottester.repository;

import com.myapp.springboottester.entity.Author;
import com.myapp.springboottester.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * DAO layer for operating with {@link Author} entity.
 *
 * @author Ivan_Semenov
 */
@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

    String TOP_AUTHORS_BY_MONTH_SOLD_BOOK_QUERY = "SELECT a.id, a.name, SUM(b.price) as month_total_price " +
            "FROM author a " +
            "INNER JOIN book b ON b.author_id=a.id " +
            "WHERE b.sold_date >:dateFrom AND b.sold_date < :dateTo " +
            "GROUP BY a.id " +
            "ORDER BY month_total_price DESC " +
            "LIMIT 5";

    @Query("SELECT b FROM Book b WHERE b.author.id = ?1 AND b.soldDate IS NOT NULL")
    List<Book> getSoldBooks(Integer authorId);

    @Query(value = TOP_AUTHORS_BY_MONTH_SOLD_BOOK_QUERY,
            nativeQuery = true)
    List<Author> getTopSellingAuthors(@Param("dateFrom") Date dateFrom,
                                      @Param("dateTo") Date dateTo);
}
