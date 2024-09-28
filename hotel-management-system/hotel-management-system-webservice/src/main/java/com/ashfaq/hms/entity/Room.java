package com.ashfaq.hms.entity;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROOM_TABLE")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roomType;
	private BigDecimal roomPrice;
	private String hotelName;
	private String details;
	private boolean isBooked;

	//
	@Lob
	private Blob photo;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BookedRoom> bookings;

	// Two types of fetch
	// Eager : it will fetch the data of bookedroom when we fetch the room
	// Lazy : it will not fetch the data of bookedroom when we fetch the room we
	// only get the data when
	// we specifically ask the bookedroom data

	public Room() {
		this.bookings = new ArrayList<>();
	}

	public void addBooking(BookedRoom booking) {
		if (bookings == null) {
			bookings = new ArrayList<>();
		}
		bookings.add(booking);
		booking.setRoom(this);
		isBooked = true;

		String bookingCode = RandomStringUtils.randomNumeric(10);
		booking.setBookingConfirmationCode(bookingCode);
	}
}
