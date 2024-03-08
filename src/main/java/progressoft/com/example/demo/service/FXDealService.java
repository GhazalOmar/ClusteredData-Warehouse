package progressoft.com.example.demo.service;

import progressoft.com.example.demo.entity.FXDealEntity;
import progressoft.com.example.demo.model.FXDeal;
import progressoft.com.example.demo.model.ServerResponse;

public interface FXDealService {
    ServerResponse fetchById(Long id);
    ServerResponse fetchAll();
    ServerResponse saveFXDeal(FXDeal fxDeal);
}
