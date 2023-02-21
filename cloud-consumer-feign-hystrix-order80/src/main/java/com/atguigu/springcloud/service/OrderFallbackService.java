package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class OrderFallbackService implements OrderHystrixService{
    @Override
    public String paymentHystrix_ok(Long id) {
        return "服务调用失败，提示来自：cloud-consumer-feign-order80   paymentHystrix_ok";
    }

    @Override
    public String paymentHystrix_timeout(Long id) {
        return "服务调用失败，提示来自：cloud-consumer-feign-order80   paymentHystrix_timeout";

    }
}
