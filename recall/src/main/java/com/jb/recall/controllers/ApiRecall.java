package com.jb.recall.controllers;

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
public class ApiRecall {
    private static final String recallUrl = "https://data.gov.il/api/3/action/datastore_" +
            "search?resource_id=36bf1404-0be4-49d2-82dc-2f1ead4a8b93&q=";
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("car/{carNumber}")
    private ResponseEntity<?> getCarRecall(@PathVariable String carNumber) {
        String response = restTemplate.getForObject(recallUrl + carNumber, String.class);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray cars = result.getJSONArray("records");
            System.out.println(cars);
            return ResponseEntity.ok(cars.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("[]", HttpStatus.BAD_REQUEST);
    }


}
