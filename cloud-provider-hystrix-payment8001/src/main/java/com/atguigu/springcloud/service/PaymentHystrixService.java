package com.atguigu.springcloud.service;

import cn.hutool.log.Log;
import org.springframework.stereotype.Service;


public interface PaymentHystrixService {
    public String paymentHystrix_ok(Long id);
    public String paymentHystrix_timeout(Long id);
    public String paymentCircuitBreaker(Integer id);

}
