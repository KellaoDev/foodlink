package com.foodlink.repository;

import com.foodlink.entity.DonationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<DonationEntity, Long> {

        List<DonationEntity> findByStatusFalse();
        List<DonationEntity> findByStatusTrue();
}
