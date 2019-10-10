package com.myapp.bookstore.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Book entity.
 *
 * @author Ivan_Semenov
 */
@Entity
@Table(name = "book")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "views")
    private Integer views;

    @Column(name = "sold_date")
    @Temporal(TemporalType.DATE)
    private Date soldDate;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(String title, Integer price) {
        this.title = title;
        this.price = price;
        this.views = 0;
    }
}
