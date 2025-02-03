package az.spring.travel.msbookings.service.impl;

import az.spring.travel.msbookings.domain.repository.FlightRepository;
import az.spring.travel.msbookings.exception.NotFoundException;
import az.spring.travel.msbookings.mapper.FlightMapper;
import az.spring.travel.msbookings.model.dto.response.FlightDto;
import az.spring.travel.msbookings.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;

    public FlightServiceImpl(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public FlightDto getFlightById(Long flightId) {
        return flightMapper.toFlightDto(flightRepository.findById(flightId)
                .orElseThrow(() -> new NotFoundException("No flight found with id: " + id)));
    }

    @Override
    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flightMapper::toFlightDto).collect(Collectors.toList());
    }
}
