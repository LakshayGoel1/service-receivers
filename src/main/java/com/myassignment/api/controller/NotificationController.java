package com.myassignment.api.controller;

import com.myassignment.api.pojo.JobRequest;
import com.myassignment.api.pojo.Order;
import com.myassignment.api.pojo.ServiceProvider;
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
@RequestMapping("/order/notification")
public class NotificationController {

    @Autowired
    private HiringService hiringService;

    @PostMapping("/booking")
    public ResponseEntity<String> bookingConfirmation(@RequestBody ServiceProvider serviceProvider)
        throws JSONException {
        log.info("Notification Received: Booking confirmed");
        log.info("Service Provider name: {}", serviceProvider.getServiceProviderName());
        log.info("Service Provider number: {}", serviceProvider.getServiceProviderNumber());
        Thread thread = new Thread(() -> {
            hiringService.sendConsumerDetailsToServiceProvider(serviceProvider.getServiceProviderNumber());
        });
        thread.start();
        JSONObject response = new JSONObject();
        response.put("status", "Booking confirmed and Consumer details sent to service provider");
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

}
