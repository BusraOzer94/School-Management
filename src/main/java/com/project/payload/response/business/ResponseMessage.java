package com.project.payload.response.business;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // Bu class in bir instance olustugunda null olanlari json dosya icine yazma anlaminda kullanilir
public class ResponseMessage <E>{

    private E object;
    private String message;
    private HttpStatus httpStatus;

}
