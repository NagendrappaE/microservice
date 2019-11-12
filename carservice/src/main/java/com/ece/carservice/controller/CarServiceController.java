package com.ece.carservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ece.carservice.service.ICarService;

@RestController
public class CarServiceController {

	
	@Autowired
	private ICarService icarService;

	@GetMapping("/data/{abc}")
	@ResponseBody
	public String we(@PathVariable("abc") String val) {

		System.out.println("hhhhhhhhhhhhh" + val);
		return val;
	}

	@GetMapping("/carpay/{cname}")
	@ResponseBody
	public String makePayment(@PathVariable("cname") String cname) {
		String response = null;
		try {
			int accNum = 111112333;
			final String url = "http://paymentservice/payment/pay";
			

			String reqStr = icarService.prepareRequest(accNum, cname);

			response = icarService.callPaymentApplication(url, reqStr, "accNum" + accNum);

		} catch (Exception e) {
			response = e.getMessage();
			e.printStackTrace();

		}

		// preparing request

		return response;
	}

}
