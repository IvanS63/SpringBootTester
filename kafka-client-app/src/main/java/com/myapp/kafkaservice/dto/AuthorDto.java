package com.myapp.kafkaservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * AuthorDto.
 *
 * @author Ivan_Semenov
 */
@Data
@Accessors(chain = true)
public class AuthorDto {
    private String name;
    private List<BookDto> books;
}
