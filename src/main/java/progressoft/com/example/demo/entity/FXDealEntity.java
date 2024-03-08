package progressoft.com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import progressoft.com.example.demo.model.FXDeal;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fx_deals")
public class FXDealEntity {

    @Id
    private Long id;
    private String fromISOCurrency;
    private String toISOCurrency;
    private LocalDateTime dealTimestamp;
    private BigDecimal amount;

    public FXDealEntity toEntity(FXDeal fxDeal){
        return FXDealEntity.builder()
                .amount(fxDeal.getAmount())
                .dealTimestamp(LocalDateTime.parse(fxDeal.getDealTimestamp()))
                .id(Long.parseLong(fxDeal.getId()))
                .fromISOCurrency(fxDeal.getFromISOCurrency())
                .toISOCurrency(fxDeal.getToISOCurrency())
                .build();
    }
}