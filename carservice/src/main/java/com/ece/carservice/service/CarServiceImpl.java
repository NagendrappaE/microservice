/**
 * 
 */
package com.ece.carservice.service;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ece.carservice.bean.CarRequest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author user
 *
 */
@Service

public class CarServiceImpl implements ICarService {

	Logger log = LoggerFactory.getLogger(CarServiceImpl.class);

	@Autowired
	public RestTemplate restTemplate;

	@Override
	public String callPaymentApplication(String url, String reqStr, String accNum) {
		String response = null;
		try {
			log.info("call to core banking system with {} ", reqStr);

			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(reqStr, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
			response = responseEntity.getBody();

			log.info("getting response from core banking system {} ", response);
		}

		catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));

		}
		return response;
	}

	@Override
	public String objectToJsonString(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		log.debug("Converting into json format");

		String jsonRequest = null;

		try {
			jsonRequest = objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return jsonRequest;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object jsonToObject(String responseStr, Class claaaName) {
		
		
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Object response = null;

		log.info("Converting json to object::::::::::::::::::: {}", responseStr);

		try {
			response = mapper.readValue(responseStr, claaaName);
		} catch (Exception e) {
			log.error(ExceptionUtils.getFullStackTrace(e));
		}
		return response;
	}

	@Override
	public String prepareRequest(int accNumNa, String car) {

		CarRequest request=new CarRequest();
		request.setAccountNumber(accNumNa);
		request.setAmount("223233");
		request.setDescription(car+"descccc");
		request.setName(car);
		
		
		
		return objectToJsonString(request);
	}

}
