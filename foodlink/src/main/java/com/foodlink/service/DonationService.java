package com.foodlink.service;

import com.foodlink.entity.DonationEntity;
import com.foodlink.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public DonationEntity saveDonation(DonationEntity donation) {
        return donationRepository.save(donation);
    }

    public List<DonationEntity> getAllDonations() {
        return donationRepository.findAll();
    }

    public DonationEntity getDonationById(long id) {
        return donationRepository.findById(id).get();
    }
}
