package com.tistory.eclipse4j.api.controller;

import com.tistory.eclipse4j.core.jpa.db1.entity.Reservation;
import com.tistory.eclipse4j.core.jpa.service.ReservationTxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ReservationController {

	private final ReservationTxService reservationTxService;

	public ReservationController(ReservationTxService reservationTxService) {
		this.reservationTxService = reservationTxService;
	}

	@ResponseBody
	@GetMapping(path = "/reservations/{reservationId}")
	public Reservation find(@PathVariable("reservationId") Long reservationId, @RequestParam("ms")long ms) {
		Reservation reservation = reservationTxService.findById(reservationId, ms);
		return reservation;
	}
	
	@ResponseBody
	@GetMapping(path = "/reservations/{reservationId}/products/{productId}/update-tx-lock")
	public Reservation findAndUpdate(@PathVariable("reservationId") Long reservationId, @PathVariable("productId") Long productId, @RequestParam("ms")long ms) {
		Reservation reservation = reservationTxService.findAndUpdate(reservationId, productId, ms);
		return reservation;
	}
}
