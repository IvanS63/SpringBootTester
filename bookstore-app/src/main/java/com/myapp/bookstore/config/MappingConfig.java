package com.myapp.bookstore.config;

import com.myapp.bookstore.entity.Author;
import com.myapp.bookstore.entity.Book;
import com.myapp.kafkaservice.dto.AuthorDto;
import com.myapp.kafkaservice.dto.BookDto;
import com.myapp.kafkaservice.dto.SendRequestDto;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.context.annotation.Configuration;

/**
 * MappingConfig.
 *
 * @author Ivan_Semenov
 */
@Configuration
@RequiredArgsConstructor
public class MappingConfig implements OrikaMapperFactoryConfigurer {

    @Override
    public void configure(MapperFactory orikaMapperFactory) {
        orikaMapperFactory.classMap(SendRequestDto.class, Author.class)
                .field("author.name", "name")
                .field("author.books", "books")
                .byDefault()
                .register();

        orikaMapperFactory.classMap(AuthorDto.class, Author.class)
                .byDefault()
                .register();
        
        orikaMapperFactory.classMap(BookDto.class, Book.class)
                .customize(
                        new CustomMapper<BookDto, Book>() {
                            public void mapAtoB(Book book, BookDto bookDto) {
                                book.setViews(0);
                                book.setPrice(1000);
                                book.setTitle(bookDto.getTitle());
                                book.setSoldDate(bookDto.getSold());
                            }
                        })
                .byDefault()
                .register();
    }
}
