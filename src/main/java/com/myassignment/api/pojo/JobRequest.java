package com.myassignment.api.pojo;

import lombok.Data;

@Data
public class JobRequest {

    private String jobDescription;

    private String consumerAddress;

    private String consumerNumber;

    private String assignedServiceProvider;

}
