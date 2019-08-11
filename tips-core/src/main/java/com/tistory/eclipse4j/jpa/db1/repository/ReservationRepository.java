package com.tistory.eclipse4j.jpa.db1.repository;

import org.springframework.data.repository.CrudRepository;

import com.tistory.eclipse4j.jpa.db1.entity.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
