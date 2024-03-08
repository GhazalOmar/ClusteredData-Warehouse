package progressoft.com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;

@Data
public class FXDeal {
    @NotEmpty(message = "ID must not be null")
    @Pattern(regexp = "^[0-9]*$", message = "ID must be numeric")
    private String id;

    @NotBlank(message = "From ISO currency must not be null")
    private String fromISOCurrency;

    @NotBlank(message = "To ISO currency must not be null")
    private String toISOCurrency;

    @NotBlank(message = "Timestamp must not be null")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$" , message = "Timestamp must be \"yyyy-MM-ddTHH:mm:ss\"")
    private String dealTimestamp;

    @NotNull(message = "Amount must not be null")
    @DecimalMin(value = "0.1", message = "Deal amount must be greater than 0")
    private BigDecimal amount;
}