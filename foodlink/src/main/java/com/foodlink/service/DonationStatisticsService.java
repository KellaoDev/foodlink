package com.foodlink.service;

import org.springframework.stereotype.Service;

@Service
public class DonationStatisticsService {

    static {
        try {
            String dllPath = "C:/Users/Kellao/Documents/foodlink/foodlink/target/classes/donation_statistics.dll";
            System.load(dllPath);
            System.out.println("Biblioteca carregada com sucesso!");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Erro ao carregar a biblioteca: " + e.getMessage());
        }

    }

    public native int getTotalDonations(int[] donationQuantities);

}
