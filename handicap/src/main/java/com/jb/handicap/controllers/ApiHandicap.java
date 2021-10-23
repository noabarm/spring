package com.jb.handicap.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api")
public class ApiHandicap {
    private static final String handiCapUrl = "https://data.gov.il/api/3/action/datastore_search?resource_id=c8b9f9c8-4612-4068-934f-d4acd2e3c06e&q=";
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("car/{carNumber}")
    private ResponseEntity<?> isHandicapCar(@PathVariable String carNumber) {
        String response = restTemplate.getForObject(handiCapUrl + carNumber, String.class);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray cars = result.getJSONArray("records");
            System.out.println(cars.length());
            return ResponseEntity.ok(cars.length() > 0 ? "TRUE" : "FALSE");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("[]", HttpStatus.BAD_REQUEST);


    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();//1046534
        String response = restTemplate.getForObject(handiCapUrl + "1046534", String.class);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray cars = result.getJSONArray("records");
            System.out.println(cars.length());
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
