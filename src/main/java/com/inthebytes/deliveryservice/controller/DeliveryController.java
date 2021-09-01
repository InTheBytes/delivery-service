package com.inthebytes.deliveryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inthebytes.deliveryservice.dto.DeliveryDto;
import com.inthebytes.deliveryservice.dto.DriverDto;
import com.inthebytes.deliveryservice.entity.Driver;
import com.inthebytes.deliveryservice.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class DeliveryController {

	@Autowired
	DeliveryService service;

	@RequestMapping(path = "/start", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<DeliveryDto> startDelivery(@RequestBody String driverId, String orderId) {

		ResponseEntity<DeliveryDto> response;
		try {

			DeliveryDto responseDto = service.start(driverId, orderId);

			if (responseDto == null) {
				response = new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
			} else {
				
				response = new ResponseEntity<>(responseDto, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	@RequestMapping(path = "/pickup", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<DeliveryDto> pickup(@RequestBody String deliveryId) {

		ResponseEntity<DeliveryDto> response;
		try {

			DeliveryDto responseDto = service.pickup(deliveryId);

			if (responseDto == null) {
				response = new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
			} else {
				
				response = new ResponseEntity<>(responseDto, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	@RequestMapping(path = "/delivered", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<DeliveryDto> delivered(@RequestBody String deliveryId) {

		ResponseEntity<DeliveryDto> response;
		try {

			DeliveryDto responseDto = service.delivered(deliveryId);

			if (responseDto == null) {
				response = new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
			} else {
				
				response = new ResponseEntity<>(responseDto, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	@RequestMapping(path = "/checkIn", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<DriverDto> checkIn(@RequestBody String driverId) {

		ResponseEntity<DriverDto> response;
		try {

			DriverDto responseDto = service.checkIn(driverId);

			if (responseDto == null) {
				response = new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
			} else {
				
				response = new ResponseEntity<>(responseDto, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	@RequestMapping(path = "/checkOut", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<DriverDto> checkOut(@RequestBody String driverId) {

		ResponseEntity<DriverDto> response;
		try {

			DriverDto responseDto = service.checkOut(driverId);

			if (responseDto == null) {
				response = new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
			} else {
				
				response = new ResponseEntity<>(responseDto, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	@RequestMapping(path = "/drivers/new", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<DriverDto> newDriver(@RequestBody Driver driver) {

		ResponseEntity<DriverDto> response;
		try {

			DriverDto responseDto = service.newDriver(driver);

			if (responseDto == null) {
				response = new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
			} else {
				
				response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}
	
	@RequestMapping(path = "/drivers/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<DriverDto> editDriver(@RequestBody Driver driver) {

		ResponseEntity<DriverDto> response;
		try {

			DriverDto responseDto = service.editDriver(driver);

			if (responseDto == null) {
				response = new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
			} else {
				
				response = new ResponseEntity<>(responseDto, HttpStatus.CREATED);
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

}
