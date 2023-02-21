package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value="cloud-provider-hystrix-payment",fallback = OrderFallbackService.class )
public interface OrderHystrixService {
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentHystrix_ok(@PathVariable("id") Long id);
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentHystrix_timeout(@PathVariable("id") Long id);
}
