package com.ashfaq.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.hms.entity.BookedRoom;

public interface BookingRepository extends JpaRepository<BookedRoom, Long> {

	List<BookedRoom> findByRoomId(Long roomId);

}
