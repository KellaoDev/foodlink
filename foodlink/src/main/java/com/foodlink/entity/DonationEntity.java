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

    @Column
    private String phoneContact;

    @Column
    private String emailContact;

    @Enumerated(EnumType.STRING)
    @Column
    private FoodCategoryEnum foodCategory;

    @Column
    private String food;

    @Column
    private String description;

    @Column
    private Integer quantity;

    @Column
    private LocalDate donationDate;

    @Column
    private LocalTime donationTime;

    private String observation;

    @Enumerated(EnumType.STRING)
    @Column
    private MethodWithdrawalEnum methodWithdrawal;

    @Column
    private Boolean terms = false;

    @Column
    private Boolean status = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
