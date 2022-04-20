package com.myassignment.api.service;

import com.myassignment.api.pojo.JobRequest;
import com.myassignment.api.pojo.Order;
import com.myassignment.api.pojo.ServiceProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class HiringService {

    @Autowired
    private RestTemplate restTemplate;

    public void submitRequestToServiceProvider(Order order, String serviceType) {
        String serviceProvider = serviceType + (int) (Math.random() * 100000);
        Thread thread = new Thread(() -> {
            restTemplate.postForObject("http://SERVICE-PROVIDERS/notification/provider/" + serviceProvider, null,
                String.class);
        });
        thread.start();
    }


    public void sendConsumerDetailsToServiceProvider(String serviceProviderNumber) {
        JobRequest jobRequest = getJobRequest(serviceProviderNumber);
        restTemplate.postForObject("http://SERVICE-PROVIDERS/notification/consumer/data", jobRequest, String.class);
    }

    public JobRequest getJobRequest(String serviceProviderNumber) {
        JobRequest jobRequest = new JobRequest();
        if (serviceProviderNumber.startsWith("E")) {
            jobRequest.setJobDescription("Home electric system failure");
        } else if (serviceProviderNumber.startsWith("T")) {
            jobRequest.setJobDescription("Need a yoga trainer");
        }

        jobRequest.setConsumerAddress("Assumed Address Street Number 1");
        jobRequest.setConsumerNumber("Assumed Contact Number 98xxxxxxxx");
        jobRequest.setAssignedServiceProvider(serviceProviderNumber);
        return jobRequest;
    }
}
