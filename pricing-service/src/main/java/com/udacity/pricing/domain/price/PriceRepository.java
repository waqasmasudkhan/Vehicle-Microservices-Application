package com.udacity.pricing.domain.price;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@RestResource(path = "/services/price")
public interface PriceRepository extends CrudRepository<Price,Long> {

    Price findDistinctByVehicleId(@RequestParam Long vehicleId);

}
