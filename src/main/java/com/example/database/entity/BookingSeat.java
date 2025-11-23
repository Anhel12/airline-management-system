package com.example.database.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking_seat")
public class BookingSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ToString.Exclude
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

        BookingSeat bookingSeat = (BookingSeat) o;
        return getId() != null && Objects.equals(getId(), bookingSeat.getId());
    }
    public final int hashcode(){
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer()
                .getPersistentClass()
                .hashCode()
                : getClass().hashCode();
    }
}
