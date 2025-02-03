package az.spring.travel.msbookings.domain.repository;

import az.spring.travel.msbookings.domain.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

}
