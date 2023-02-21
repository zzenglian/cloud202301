package com.atguigu.springcloud.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    public static final String INVOKE_URL="http://consul-provider-payment";
    @Autowired
    private RestTemplate restTemplate;
    @GetMapping("/consumer/payment/consul")
    public String paymentInfo(){
     String result=restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        System.out.println("消费者调用支付服务（consul）"+result);
        return result;
    }
}
