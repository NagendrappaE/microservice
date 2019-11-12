package com.ece.payment.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ece.payment.bean.CarRequest;
import com.ece.payment.bean.CarResponse;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private Environment environment;
	

	@PostMapping(path = "/pay", produces = "application/json", consumes = "application/json")
	@ResponseBody
	public CarResponse payCarBill(@RequestBody CarRequest reques) {

		CarResponse resp = new CarResponse();
		
		int port= Integer.parseInt(environment.getProperty("local.server.port"));
		 System.out.println("Request came ::::" + reques.getDescription()+"the PORT::"+port);
		try {
			resp.setName(reques.getName()+"serving with the port \t"+port);
			resp.setStatus("Payment has been done for carName" + resp.getName() + "A/C num \t" + reques.getAccountNumber());
		} catch (Exception e) {
			resp.setStatus("FAILED:" + e.getMessage());
			e.printStackTrace();

		}

		return resp;

	}

	@GetMapping("/ab")
	public String welcome(@PathParam("nag") String m) {

		return "paymentsert";
	}

}
