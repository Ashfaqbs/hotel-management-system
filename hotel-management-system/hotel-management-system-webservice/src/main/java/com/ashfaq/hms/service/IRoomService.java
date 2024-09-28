package com.ashfaq.hms.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.ashfaq.hms.entity.Room;

public interface IRoomService {
	public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws SQLException, IOException;

	public List<Room> getAllRooms();

	List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

	Optional<Room> getRoomById(Long roomId);

	byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException;

}
