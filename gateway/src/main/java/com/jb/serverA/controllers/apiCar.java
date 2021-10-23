package com.jb.serverA.controllers;

import com.jb.serverA.beans.Car;
import com.jb.serverA.beans.Recall;
import lombok.RequiredArgsConstructor;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api")
public class apiCar {
    @Autowired
    private RestTemplate restTemplate;

    //final String carURL = "https://data.gov.il/api/3/action/datastore_search?resource_id=053cea08-09bc-40ec-8f7a-156f0677aff3&q=";
    final String carUrl = "http://localhost:8100/api/car/";
    final String handiCapUrl = "http://localhost:8200/api/car/";
    final String recallUrl = "http://localhost:8300/api/car/";
    final String offroadURL = "http://localhost:8400/api/car/";

    @GetMapping("car/{carNumber}")
    private ResponseEntity<?> getCarDetails(@PathVariable String carNumber) {
        Car response;
        try {
            response = restTemplate.getForObject(carUrl + carNumber, Car.class);
            System.out.println(response);
        } catch (Exception err){
            response = restTemplate.getForObject(offroadURL + carNumber, Car.class);
            System.out.println("הרכב הורד מהכביש - אוי אוי אוי");
            System.out.println(response);
            response.setOffRoad(true);
        }
        //restTemplate -> handicap
        response.setHandiCap(restTemplate.getForObject(handiCapUrl + carNumber, String.class).equals("TRUE"));

        //recall
        response.setRecalls(getRecallList(restTemplate.getForObject(recallUrl + carNumber, String.class)));

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    private List<Recall> getRecallList(String restString) {
        //create new array list
        List<Recall> carRecalls = new ArrayList<>();
        //use checked exception for JSON format
        try {
            //array of our reclass from string object
            JSONArray recallArray = new JSONArray(restString);
            //iterate on each JSONobject in our array
            for (int counter = 0; counter < recallArray.length(); counter += 1) {
                //get single json object from the array by counter
                JSONObject singleItem = recallArray.getJSONObject(counter);
                //getting the date
                String[] dateString = singleItem.getString("TAARICH_PTICHA").split("T");
                //2012-12-10T00:00:00 -> split T
                //p[0] 2012-12-10
                //p[1] 00:00:00

                //add new Recall item, by pointing to json object
                carRecalls.add(new Recall(
                        singleItem.getInt("RECALL_ID"),
                        singleItem.getString("SUG_RECALL"),
                        singleItem.getString("SUG_TAKALA"),
                        singleItem.getString("TEUR_TAKALA"),
                        //singleItem.getString("TAARICH_PTICHA")
                        Date.valueOf(dateString[0])
                ));
                System.out.println(singleItem);
            }
        } catch (JSONException e) {
            System.out.println("Json format is invalid");
        }
        //return our shiny new list
        return carRecalls;
    }
}
