package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
//    public static final String PaymentSrv_URL="http://localhost:8001";
    public static final String PaymentSrv_URL="http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;
    @Resource
    LoadBalancer loadBalancer;
    @Resource
    DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        //注意restTemplate使用的方法
        CommonResult commonResult = restTemplate.postForObject(PaymentSrv_URL + "/payment/create", payment, CommonResult.class);
        return commonResult;
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id")Long id){
        //注意restTemplate使用的方法
        CommonResult commonResult = restTemplate.getForObject(PaymentSrv_URL + "/payment/get/" + id, CommonResult.class,id);
        return commonResult;
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult getPaymentById2(@PathVariable("id")Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PaymentSrv_URL + "/payment/get/" + id, CommonResult.class);
        if(entity.getStatusCode().is2xxSuccessful()){
            log.info(entity.getStatusCode()+"\t"+entity.hashCode());
            return entity.getBody();
        }else{
            return new CommonResult(444,"操作失败！");
        }
    }

    @GetMapping("/consumer/payment/lb")
    public String getPaymentLb() {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        if (instances == null || instances.size() <= 0) {
            return null;
        } else {
            ServiceInstance serviceInstance = loadBalancer.instances(instances);
            URI uri = serviceInstance.getUri();
            return restTemplate.getForObject(uri + "/payment/lb", String.class);
        }
    }

    }

