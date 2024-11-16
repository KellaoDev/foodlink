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

    @Autowired
    private DonationStatisticsService donationStatisticsService;

    public DonationEntity saveDonation(DonationEntity donation) {
        if (donation.getNameRestaurant() == null || donation.getNameRestaurant().isEmpty()) {
            throw new IllegalArgumentException("Nome de restaurante não pode ser nulo ou vazio");
        }

        if (donation.getAddress() == null || donation.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser nulo ou vazio");
        }

        if (donation.getPhoneContact() == null || donation.getPhoneContact().isEmpty()) {
            throw new IllegalArgumentException("Telefone de contato não pode ser nulo ou vazio");
        }

        if (donation.getEmailContact() == null || donation.getEmailContact().isEmpty()) {
            throw new IllegalArgumentException("Email de contato não pode ser nulo ou vazio");
        }

        if (donation.getFoodCategory() == null) {
            throw new IllegalArgumentException("Categoria de alimento não pode ser nula");
        }

        if (donation.getFood() == null || donation.getFood().isEmpty()) {
            throw new IllegalArgumentException("Tipo de alimento não pode ser nulo ou vazio");
        }

        if (donation.getQuantity() == null || donation.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }

        if (donation.getDonationDate() == null) {
            throw new IllegalArgumentException("Data da doação não pode ser nula");
        }

        if (donation.getDonationTime() == null) {
            throw new IllegalArgumentException("Hora da doação não pode ser nula");
        }

        if (donation.getMethodWithdrawal() == null) {
            throw new IllegalArgumentException("Método de retirada não pode ser nulo");
        }

        if (donation.getTerms() == null || !donation.getTerms()) {
            throw new IllegalArgumentException("Termos devem ser aceitos");
        }

        return donationRepository.save(donation);
    }

    public void acceptDonation(Long donationId) {
        Optional<DonationEntity> optionalDonation = donationRepository.findById(donationId);

        if (optionalDonation.isPresent()) {
            DonationEntity donation = optionalDonation.get();
            donation.setStatus(true);
            donationRepository.save(donation);
        } else {
            throw new IllegalArgumentException("Doação com ID " + donationId + " não encontrada.");
        }
    }

    public List<DonationEntity> getAllDonations() {
        return donationRepository.findAll();
    }

    public DonationEntity getDonationById(long id) {
        return donationRepository.findById(id).get();
    }

    public List<DonationEntity> getAvailableDonations() {
        return donationRepository.findByStatusFalse();
    }

    public List<DonationEntity> getReceiveDonations() { return donationRepository.findByStatusTrue(); }

    public int getTotalDonationsByRestaurant(String nameRestaurant) {
        List<DonationEntity> donations = donationRepository.findByNameRestaurant(nameRestaurant);

        int [] donationQuantities = donations.stream().mapToInt(DonationEntity::getQuantity).toArray();

        return donationStatisticsService.getTotalDonations(donationQuantities);
    }

    public List<DonationEntity> findByName(String name) {
        return donationRepository.findByNameRestaurant(name);
    }

}
