package com.myassignment.api.controller;

import com.myassignment.api.pojo.Order;
import com.myassignment.api.service.HiringService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order/hire")
public class HiringController {

    @Autowired
    private HiringService hiringService;

    @PostMapping("/electrician")
    public ResponseEntity<String> hireElectrician(@RequestBody Order order) throws JSONException {
        Thread thread = new Thread(() -> {
            hiringService.submitRequestToServiceProvider(order, "E");
        });
        thread.start();
        log.info("Hiring electrician");
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("data", "Request for hiring electrician successfully submitted");
        return new ResponseEntity<>(response.toString(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/trainer")
    public ResponseEntity<String> hireYogaTrainer(@RequestBody Order order) throws JSONException {
        Thread thread = new Thread(() -> {
            hiringService.submitRequestToServiceProvider(order, "T");
        });
        thread.start();
        log.info("Hiring trainer");
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("data", "Request for hiring yoga trainer successfully submitted");
        return new ResponseEntity<>(response.toString(), HttpStatus.ACCEPTED);
    }
}
