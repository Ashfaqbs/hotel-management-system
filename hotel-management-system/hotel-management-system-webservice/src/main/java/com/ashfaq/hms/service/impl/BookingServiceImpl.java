package com.ashfaq.hms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashfaq.hms.entity.BookedRoom;
import com.ashfaq.hms.entity.Room;
import com.ashfaq.hms.repository.BookingRepository;
import com.ashfaq.hms.service.IBookingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookingService {
	private final BookingRepository bookingRepository;

	private final RoomServiceImpl roomService;

	public List<BookedRoom> getAllBookings() {
		return bookingRepository.findAll();
	}

	public void cancelBooking(Long bookingId) {
		bookingRepository.deleteById(bookingId);
	}

	public String saveBooking(Long roomId, BookedRoom bookingRequest) {
		if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
			throw new RuntimeException("Please provide valid date.");
		}

		Room room = roomService.getRoomById(roomId).get();
		List<BookedRoom> existingBookings = room.getBookings();

		if (!roomIsAvailable(bookingRequest, existingBookings)) {
			throw new RuntimeException("This room is not available for the selected dates.");
		}
		
		room.addBooking(bookingRequest);
		bookingRepository.save(bookingRequest);
		
		return bookingRequest.getBookingConfirmationCode();
	}
	
	public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
		return bookingRepository.findByRoomId(roomId);
	}

	public boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
		return existingBookings.stream()
				.noneMatch(existingBooking -> bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
						|| bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
						|| (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
								&& bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
						|| (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

								&& bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
						|| (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

								&& bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

						|| (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
								&& bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

						|| (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
								&& bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate())));
	}
}
