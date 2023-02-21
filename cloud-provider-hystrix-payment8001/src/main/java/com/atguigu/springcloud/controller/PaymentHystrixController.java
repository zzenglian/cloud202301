package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentHystrixController {
    @Resource
    PaymentHystrixService paymentHystrixService;
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentHystrix_ok(@PathVariable("id") Long id) {
        log.info("****0k,id:"+id);
        return paymentHystrixService.paymentHystrix_ok(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentHystrix_timeout(@PathVariable("id") Long id) {
        log.info("****timeout,id:"+id);
        return paymentHystrixService.paymentHystrix_timeout(id);
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentCircuitBreaker(id);
        log.info("****result: "+result);
        return result;
    }


}
