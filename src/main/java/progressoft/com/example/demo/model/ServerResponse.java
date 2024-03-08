package progressoft.com.example.demo.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ServerResponse {
    private int statusCode;
    private String message;
    private Object data;
}
