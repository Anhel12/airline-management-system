package com.example.database.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking_seat")
public class BookingSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @OneToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    public void setBooking(Booking booking){
        this.booking = booking;
        this.booking.getBookingSeatList().add(this);
    }

    public void setSeat(Seat seat){
        this.seat = seat;
        this.seat.setBookingSeat(this);
    }
}
