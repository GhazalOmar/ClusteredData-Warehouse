package progressoft.com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import progressoft.com.example.demo.entity.FXDealEntity;
import progressoft.com.example.demo.exception.DealNotFoundException;
import progressoft.com.example.demo.exception.DuplicateDealException;
import progressoft.com.example.demo.exception.InvalidCurrencyException;
import progressoft.com.example.demo.model.FXDeal;
import progressoft.com.example.demo.model.ServerResponse;
import progressoft.com.example.demo.repository.FXDealRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

class FXDealServiceImplTest {

    private FXDealRepository fxDealRepository;
    private FXDealServiceImpl fxDealService;

    @BeforeEach
    public void setup() {
        fxDealRepository = mock(FXDealRepository.class);
        fxDealService = new FXDealServiceImpl(fxDealRepository);
    }

    @Test
    void should_be_valid_when_fetch_by_id() {
        Long id = 1L;
        FXDealEntity entity = buildEntity(id);

        when(fxDealRepository.findById(id)).thenReturn(Optional.of(entity));

        ServerResponse response = fxDealService.fetchById(id);

        verify(fxDealRepository).findById(id);

        assertEquals(OK.value(), response.getStatusCode());
        assertEquals("Deal with id [1] is successfully retrieved", response.getMessage());
    }

    @Test
    void when_fetch_by_id_then_throw_exception() {
        Long id = 9999L;

        when(fxDealRepository.findById(id)).thenReturn(Optional.empty());

        DealNotFoundException exception = assertThrows(DealNotFoundException.class, () ->
                fxDealService.fetchById(id));

        assertEquals("Deal with ID [9999] not found", exception.getMessage());
    }

    @Test
    void should_be_valid_when_fetch_all() {
        FXDealEntity entity1 = buildEntity(1L);
        FXDealEntity entity2 = buildEntity(2L);
        FXDealEntity entity3 = buildEntity(3L);
        FXDealEntity entity4 = buildEntity(4L);

        when(fxDealRepository.findAll()).thenReturn(List.of(entity1, entity2, entity3, entity4));

        ServerResponse response = fxDealService.fetchAll();
        System.out.println(response.getData());

        assertEquals(OK.value(), response.getStatusCode());
        assertEquals("Deals were retrieved successfully", response.getMessage());
    }

    @Test
    void when_fetch_all_then_return_no_deals_found() {
        when(fxDealRepository.findAll()).thenReturn(List.of());

        ServerResponse response = fxDealService.fetchAll();

        assertEquals(NO_CONTENT.value(), response.getStatusCode());
        assertEquals("No deals were found", response.getMessage());
    }

    @Test
    void success_deal_save() {
        FXDeal deal = buildModel("5");
        FXDealEntity entity = buildEntity(5L);

        when(fxDealRepository.findById(5L)).thenReturn(Optional.empty());

        ServerResponse response = fxDealService.saveFXDeal(deal);

        verify(fxDealRepository).save(entity);

        assertEquals(CREATED.value(), response.getStatusCode());
        assertEquals("Deal with id [5] successfully saved", response.getMessage());
    }

    @Test
    void when_is_not_valid_from_currency_throw_exception() {
        FXDeal deal = buildModel("3");
        deal.setFromISOCurrency("invalid");

        FXDealEntity entity = buildEntity(3L);

        when(fxDealRepository.findById(entity.getId())).thenReturn(Optional.empty());

        InvalidCurrencyException exception = assertThrows(InvalidCurrencyException.class,
                () -> fxDealService.saveFXDeal(deal));

        verify(fxDealRepository, never()).save(entity);

        assertEquals("Invalid from ISO currency code [" + deal.getFromISOCurrency() + "]", exception.getMessage());
    }

    @Test
    void when_is_not_valid_to_currency_throw_exception() {
        FXDeal deal = buildModel("3");
        deal.setToISOCurrency("invalid");

        FXDealEntity entity = buildEntity(3L);

        when(fxDealRepository.findById(entity.getId())).thenReturn(Optional.empty());

        InvalidCurrencyException exception = assertThrows(InvalidCurrencyException.class,
                () -> fxDealService.saveFXDeal(deal));

        verify(fxDealRepository, never()).save(entity);

        assertEquals("Invalid to ISO currency code [invalid]", exception.getMessage());
    }

    @Test
    void should_throw_exception_when_deal_is_duplicated() {
        FXDeal deal = buildModel("3");
        FXDealEntity entity = buildEntity(3L);

        when(fxDealRepository.findById(entity.getId())).thenReturn(Optional.of(entity));

        DuplicateDealException exception = assertThrows(DuplicateDealException.class, () ->
                fxDealService.saveFXDeal(deal));

        verify(fxDealRepository, never()).save(entity);

        assertEquals("Duplicated FX Deal with ID [3]", exception.getMessage());
    }

    private FXDealEntity buildEntity(Long id) {
        return FXDealEntity.builder()
                .id(id)
                .amount(BigDecimal.TEN)
                .dealTimestamp(LocalDateTime.parse("2022-01-12T12:12:12"))
                .fromISOCurrency("JOD")
                .toISOCurrency("USD")
                .build();
    }

    private FXDeal buildModel(String id) {
        FXDeal deal = new FXDeal();
        deal.setId(id);
        deal.setDealTimestamp("2022-01-12T12:12:12");
        deal.setFromISOCurrency("JOD");
        deal.setToISOCurrency("USD");
        deal.setAmount(BigDecimal.TEN);
        return deal;
    }
}