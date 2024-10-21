package com.foodlink.entity;

import com.foodlink.enumerated.FoodCategoryEnum;
import com.foodlink.enumerated.MethodWithdrawalEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nameRestaurant;

    private String address;

    private String phoneContact;

    private String emailContact;

    @Enumerated(EnumType.STRING)
    private FoodCategoryEnum foodCategory;

    private String food;

    private String description;

    private Integer quantity;

    private LocalDate donationDate;

    private LocalTime donationTime;

    private String observation;

    @Enumerated(EnumType.STRING)
    private MethodWithdrawalEnum methodWithdrawal;

    private Boolean terms = false;

    private Boolean status;

}
