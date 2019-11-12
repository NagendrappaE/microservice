/**
 * 
 */
package com.ece.carservice.service;

import org.springframework.stereotype.Service;

/**
 * @author user
 *
 */
public interface ICarService {

	
	public String callPaymentApplication(String url,String reqStr,String accNum);
	
	
	public String objectToJsonString(Object obj);
	
	

	public Object jsonToObject(String mandateDataStr, Class clazz);	
	
	
	public String prepareRequest(int accNumNa, String car);
	
}
