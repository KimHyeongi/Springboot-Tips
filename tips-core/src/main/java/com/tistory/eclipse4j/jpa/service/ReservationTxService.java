package com.tistory.eclipse4j.jpa.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tistory.eclipse4j.jpa.ThreadSleep;
import com.tistory.eclipse4j.jpa.db1.entity.Reservation;
import com.tistory.eclipse4j.jpa.db1.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationTxService {

	private final ReservationRepository reservationRepository;

	@Transactional(readOnly = false)
	public Reservation findAndUpdate(Long id, Long productId, long ms) {
		Reservation reservation = reservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException(String.valueOf(id)));
		ThreadSleep.sleep(ms);
		reservation.setProductId(productId);
		reservationRepository.save(reservation);
		return reservation;
	}

	@Transactional(readOnly = true)
	public Reservation findById(Long id, long ms) {
		Reservation reservation = reservationRepository.findById(id).orElseThrow(()->new EntityNotFoundException(String.valueOf(id)));
		ThreadSleep.sleep(ms);
		return reservation;
	}
}
