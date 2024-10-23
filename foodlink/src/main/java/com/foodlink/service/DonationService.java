package com.foodlink.service;

import com.foodlink.entity.DonationEntity;
import com.foodlink.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

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
        DonationEntity donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new IllegalArgumentException("Doação não encontrada com id: " + donationId));

        donation.setStatus(true);
        donationRepository.save(donation);
    }

    public List<DonationEntity> getAllDonations() {
        return donationRepository.findAll();
    }

    public DonationEntity getDonationById(long id) {
        return donationRepository.findById(id).get();
    }

    public List<DonationEntity> getAcceptDonationsForOng() {
        return donationRepository.findByStatus(true);
    }

    public List<DonationEntity> getAvailableDonations() {
        return donationRepository.findByStatus(false);
    }
}
