package com.mederp.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "sales")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate saleDate;

    private String shopName;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private boolean isSupplied;

    private boolean isReturned;

}
