package com.myapp.kafkaservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * BookDto.
 *
 * @author Ivan_Semenov
 */
@Data
@Accessors(chain = true)
public class BookDto {
    private String title;
    private Date sold;
}
