package com.ashfaq.hms.service;

import java.util.List;

import com.ashfaq.hms.entity.BookedRoom;

public interface IBookingService {
	List<BookedRoom> getAllBookings();

	void cancelBooking(Long bookingId);

	String saveBooking(Long roomId, BookedRoom bookingRequest);

	List<BookedRoom> getAllBookingsByRoomId(Long roomId);

	boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings);

}
