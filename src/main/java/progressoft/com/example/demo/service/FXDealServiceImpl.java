package progressoft.com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import progressoft.com.example.demo.entity.FXDealEntity;
import progressoft.com.example.demo.exception.DealNotFoundException;
import progressoft.com.example.demo.exception.DuplicateDealException;
import progressoft.com.example.demo.exception.InvalidCurrencyException;
import progressoft.com.example.demo.model.FXDeal;
import progressoft.com.example.demo.model.ServerResponse;
import progressoft.com.example.demo.repository.FXDealRepository;

import java.util.Currency;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FXDealServiceImpl implements FXDealService {

    private final FXDealRepository fxDealRepository;

    @Override
    public ServerResponse fetchById(long id) {
        log.info("Trying to fetch deal with ID {}", id);

        FXDealEntity entity = fxDealRepository.findById(id)
                .orElseThrow(() -> new DealNotFoundException("Deal with ID [" + id + "] not found"));

        log.info("Deal with id {} is successfully retrieved", id);

        return new ServerResponse(HttpStatus.OK.value(),
                "Deal with id [" + id + "] is successfully retrieved", entity);
    }

    @Override
    public  ServerResponse fetchAll() {
        log.info("Trying to fetch all deals");

        List<FXDealEntity> entities = fxDealRepository.findAll();

        if(entities.isEmpty()){
            log.info("No deals were found");
            return new ServerResponse(HttpStatus.NO_CONTENT.value(), "No deals were found", entities);
        }

        log.info("Deals were retrieved successfully");

        return new ServerResponse(HttpStatus.OK.value(), "Deals were retrieved successfully" , entities);
    }

    @Override
    public ServerResponse saveFXDeal(FXDeal fxDeal) {
        FXDealEntity entity = new FXDealEntity().toEntity(fxDeal);

        Long fxDealId = entity.getId();
        String fromCurrency = entity.getFromISOCurrency();
        String toCurrency = entity.getToISOCurrency();

        checkCurrencyValidity(fromCurrency, toCurrency);

        log.info("Trying to save FX Deal with ID [{}]...", fxDealId);
        fxDealRepository.findById(fxDealId)
                .orElseThrow(() -> new DuplicateDealException("Duplicated FX Deal with ID [" + fxDealId + "]"));

        fxDealRepository.save(entity);

        log.info("Deal with ID {} successfully saved", fxDealId);

        return new ServerResponse(HttpStatus.CREATED.value(),
                "Deal with id [" + fxDealId + "] successfully saved", entity);
    }

    private void checkCurrencyValidity(String fromISOCurrency, String toISOCurrency) {

        log.info("Checking currencies: fromCurrencyCode={}, toCurrencyCode={}", fromISOCurrency, toISOCurrency);

        if(!isValidCurrency(fromISOCurrency)){
            throw new InvalidCurrencyException("Invalid from ISO currency code [" + fromISOCurrency +"]");
        }
        if(!isValidCurrency(toISOCurrency)){
            throw new InvalidCurrencyException("Invalid currency code [" + toISOCurrency +"]");
        }
    }

    private boolean isValidCurrency(String currency) {
        try {
            Currency.getInstance(currency);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
