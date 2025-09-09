package com.mederp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
// ApiResponse<String>
public class ApiResponse<T> {

    private boolean success;
    // private  int statusCode;

    private String message;

    private LocalDateTime timestamp;

    private T data;

    // public static Object builder() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'builder'");
    // }

}
