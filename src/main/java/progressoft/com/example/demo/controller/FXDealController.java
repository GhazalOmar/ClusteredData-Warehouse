package progressoft.com.example.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import progressoft.com.example.demo.model.FXDeal;
import progressoft.com.example.demo.model.ServerResponse;
import progressoft.com.example.demo.service.FXDealService;

@RestController
@RequestMapping("/api/v1/deal")
@RequiredArgsConstructor
public class FXDealController {

    private final FXDealService fxDealService;

    @GetMapping("/fetch/{id}")
    public ResponseEntity<ServerResponse> fetchDealById(@PathVariable Long id){
        ServerResponse response = fxDealService.fetchById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/fetch-all")
    public ResponseEntity<ServerResponse> fetchAllDeals(){
        ServerResponse response = fxDealService.fetchAll();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ServerResponse> saveDeal(@Valid @RequestBody FXDeal fxDeal){
        ServerResponse response = fxDealService.saveFXDeal(fxDeal);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}