package com.jb.serverB.controllers;


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
public class restAPI {
    @Autowired
    private RestTemplate restTemplate;

    final static String carURL = "https://data.gov.il/api/3/action/datastore_search?resource_id=053cea08-09bc-40ec-8f7a-156f0677aff3&q=";

    @GetMapping("car/{carNumber}")
    private ResponseEntity<?> getCarNumber(@PathVariable String carNumber) {
        String response = restTemplate.getForObject(carURL + carNumber, String.class);
        JSONArray records = new JSONArray();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            records = result.getJSONArray("records");
            System.out.println(records.get(0));
            return ResponseEntity.ok(records.get(0).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("[]", HttpStatus.BAD_REQUEST);


    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(carURL + "7516667", String.class);
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray records = result.getJSONArray("records");
            System.out.println(records.get(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}


