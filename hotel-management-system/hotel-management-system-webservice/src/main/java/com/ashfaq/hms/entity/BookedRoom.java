package com.ashfaq.hms.entity;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BOOKEDROOM_TABLE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "bookingId")
public class BookedRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@Column(name = "check_in")
	private LocalDate checkInDate;

	@Column(name = "check_out")
	private LocalDate checkOutDate;

	@Column(name = "guest_fullName")
	private String guestFullName;

	@Column(name = "guest_email")
	private String guestEmail;

	@Column(name = "adults")
	private int NumOfAdults;

	@Column(name = "children")
	private int NumOfChildren;

	@Column(name = "total_guest")
	private int totalNumOfGuest;

	@Column(name = "confirmation_Code")
	private String bookingConfirmationCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;

}
