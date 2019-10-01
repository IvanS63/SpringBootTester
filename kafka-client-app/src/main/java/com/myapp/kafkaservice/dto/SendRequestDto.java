package com.myapp.kafkaservice.dto;

import com.myapp.kafkaservice.dto.enums.UpdateEventType;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * SendRequestDto.
 *
 * @author Ivan_Semenov
 */
@Data
@Accessors(chain = true)
public class SendRequestDto {
    private UpdateEventType updateEventType;
    private AuthorDto author;
}
