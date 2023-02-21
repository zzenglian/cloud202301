package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;


public interface LoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
