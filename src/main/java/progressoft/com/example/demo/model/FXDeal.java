package progressoft.com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class FXDeal {
    @NotEmpty(message = "ID is required")
    @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "ID must be numeric")
    private long id;

    @NotEmpty(message = "From ISO currency is required")
    private String fromISOCurrency;

    @NotEmpty(message = "To ISO currency is required")
    private String toISOCurrency;

    @NotEmpty(message = "Timestamp is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$" , message = "Timestamp must be \"yyyy-MM-ddTHH-mm-ss\"")
    private LocalDateTime dealTimestamp;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", message = "Deal amount must be greater than 0")
    private BigDecimal amount;
}
