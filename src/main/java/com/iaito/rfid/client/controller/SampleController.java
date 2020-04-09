package com.iaito.rfid.client.controller;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.iaito.rfid.client.SSLUtil;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Ofbiz Adapter", description = "Operations pertaining to communicate with Ofbiz Plugin.")
public class SampleController {
	private static final Logger log = LoggerFactory.getLogger(SampleController.class);
	@Value("${base.url}")
	private String baseURL;
	@GetMapping("/")
	public String sayHello() {
		return "Hello";
	}
	@PostMapping(value="/ofbiz", consumes = "application/json", produces = "application/json")
	public Map<String, Object> login(@RequestBody Map user) throws KeyManagementException, NoSuchAlgorithmException{
		RestTemplate restTemplate = new RestTemplate();
		String url = baseURL + "handHeldLogin";
		Map<String, Object> map = new HashMap<String, Object>(); 
		SSLUtil.turnOffSslChecking();
		ResponseEntity<Map> resp = restTemplate.postForEntity(url,user,Map.class);
		if(resp != null) {
			map.put("resp",resp.getBody().get("resp"));
			/**Pending Null Test for the response body.**/
			if(resp.getBody().get("resp").equals("FAIL")) {
				map.put("msg",resp.getBody().get("msg"));
			}else {
				map.put("assignments",resp.getBody().get("assignments"));
				map.put("externalLoginKey", resp.getBody().get("externalLoginKey"));
			}
			map.put("USERNAME",resp.getBody().get("USERNAME"));
			map.put("_AUTO_LOGIN_LOGOUT_",  resp.getBody().get("_AUTO_LOGIN_LOGOUT_"));
			map.put("_LOGIN_PASSED_",  resp.getBody().get("_LOGIN_PASSED_"));
			if(resp.getBody().get("userLogin") != null) {
				Map<String, Object> obj = (Map<String, Object>) resp.getBody().get("userLogin");
				map.put("partyId", obj.get("partyId"));
			}
		}else {
			map.put("response", "Null pointer");
		}
		return map;
	}
	@PostMapping(value="/locationTagging", consumes = "application/json", produces = "application/json")
	public Map<String, Object> locationTagging(@RequestBody Map tagLocation, @RequestParam(value = "externalLoginKey") String externalLoginKey) throws KeyManagementException, NoSuchAlgorithmException {
		RestTemplate restTemp = new RestTemplate();
		System.out.println(externalLoginKey);
		System.out.println(tagLocation);
		String url = baseURL + "tagLocation?externalLoginKey="+externalLoginKey;
		SSLUtil.turnOffSslChecking();
		ResponseEntity<Map> resp = restTemp.postForEntity(url, tagLocation, Map.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response", resp.getBody().get("response"));
		System.out.println(map);
		return map;
	}
	@PostMapping(value="/existingItemTagging", consumes = "application/json", produces = "application/json")
	public Map<String, Object> existingItemTagging(@RequestBody Map tagLocation, @RequestParam(value = "externalLoginKey") String externalLoginKey) throws KeyManagementException, NoSuchAlgorithmException {
		RestTemplate restTemp = new RestTemplate();
		System.out.println(externalLoginKey);
		System.out.println(tagLocation);
		String url = baseURL + "tagExistingItem?externalLoginKey="+externalLoginKey;
		SSLUtil.turnOffSslChecking();
		ResponseEntity<Map> resp = restTemp.postForEntity(url, tagLocation, Map.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response", resp.getBody().get("response"));
		System.out.println(map);
		return map;
	}
	@PostMapping(value="/recieveItemTagging", consumes = "application/json", produces = "application/json")
	public Map<String, Object> recieveItemTagging(@RequestBody Map tagLocation, @RequestParam(value = "externalLoginKey") String externalLoginKey) throws KeyManagementException, NoSuchAlgorithmException {
		RestTemplate restTemp = new RestTemplate();
		System.out.println(externalLoginKey);
		System.out.println(tagLocation);
		String url = baseURL + "tagReceivedItem?externalLoginKey="+externalLoginKey;
		SSLUtil.turnOffSslChecking();
		ResponseEntity<Map> resp = restTemp.postForEntity(url, tagLocation, Map.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response", resp.getBody().get("response"));
		System.out.println(map);
		return map;
	}
	@PostMapping(value="/despatchItem", consumes = "application/json", produces = "application/json")
	public Map<String, Object> despatchItem(@RequestBody Map tagLocation, @RequestParam(value = "externalLoginKey") String externalLoginKey) throws KeyManagementException, NoSuchAlgorithmException {
		RestTemplate restTemp = new RestTemplate();
		System.out.println(externalLoginKey);
		System.out.println(tagLocation);
		String url = baseURL + "moveInventoryItem?externalLoginKey="+externalLoginKey;
		SSLUtil.turnOffSslChecking();
		ResponseEntity<Map> resp = restTemp.postForEntity(url, tagLocation, Map.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response", resp.getBody().get("response"));
		System.out.println(map);
		return map;
	}
	@PostMapping(value="/verifyItem", consumes = "application/json", produces = "application/json")
	public Map<String, Object> verifyItemItem(@RequestBody Map tagLocation, @RequestParam(value = "externalLoginKey") String externalLoginKey) throws KeyManagementException, NoSuchAlgorithmException {
		RestTemplate restTemp = new RestTemplate();
		System.out.println(externalLoginKey);
		System.out.println(tagLocation);
		String url = baseURL + "moveInventoryItem?externalLoginKey="+externalLoginKey;
		SSLUtil.turnOffSslChecking();
		ResponseEntity<Map> resp = restTemp.postForEntity(url, tagLocation, Map.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response", resp.getBody().get("response"));
		System.out.println(map);
		return map;
	}
	
}