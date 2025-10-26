package com.example.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "aircraft")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(name = "registration_number",nullable = false , unique = true)
    private String registrationNumber;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Builder.Default
    @OneToMany(mappedBy = "aircraft")
    private List<Seat> seatList = new ArrayList<>();
}
