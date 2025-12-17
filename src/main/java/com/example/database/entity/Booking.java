package com.example.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false)
    private String bookingNumber;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "booking", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookingPassenger> bookingPassengerList = new ArrayList<>();

    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "booking", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BookingSeat> bookingSeatList = new ArrayList<>();

    public final boolean equals(Object o){
        if(this == o) return true;
        if(o == null) return false;

        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer()
                .getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;

        Booking booking = (Booking) o;
        return getId() != null && Objects.equals(getId(), booking.getId());
    }
    public final int hashcode(){
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                .hashCode()
                : getClass().hashCode();
    }
}
