package com.laowengs.vuedashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eurekaCenter")
public class EurekaController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/services")
    public Map<String,List<ServiceInstance>> serviceInfo(){
        Map<String,List<ServiceInstance>> listServiceInstanceGroupByServiceId = new HashMap<>();
        List<String> serviceNames = discoveryClient.getServices();
        for(String serviceName : serviceNames){
            listServiceInstanceGroupByServiceId.put(serviceName,discoveryClient.getInstances(serviceName));
        }
        return listServiceInstanceGroupByServiceId;
    }

}