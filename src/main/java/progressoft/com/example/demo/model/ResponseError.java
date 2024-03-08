package progressoft.com.example.demo.model;

import lombok.Data;

@Data
public class ResponseError {
    private int errorCode;
    private String errorDescription;
}