package progressoft.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progressoft.com.example.demo.entity.FXDealEntity;

@Repository
public interface FXDealRepository extends JpaRepository<FXDealEntity, Long> {
}
