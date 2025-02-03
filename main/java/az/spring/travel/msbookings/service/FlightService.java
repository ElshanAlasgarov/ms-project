package az.spring.travel.msbookings.service;

import az.spring.travel.msbookings.model.dto.response.FlightDto;

import java.util.List;

public interface FlightService {

    FlightDto getFlightById(Long flightId);

    List<FlightDto> getAllFlights();
}
