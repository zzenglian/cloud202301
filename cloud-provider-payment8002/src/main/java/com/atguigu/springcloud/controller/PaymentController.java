package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {
    @Resource
   private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("数据插入中。。");
        if(result>0){
            return new CommonResult(200,"数据插入成功，端口："+serverPort,result);
        }
        else{
            return  new CommonResult(404,"数据插入失败");
        }
    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment==null){
            return new CommonResult(404,"该数据不存在");
        }
        else{
            return  new CommonResult(200,"获取数据成功，端口："+serverPort,payment);
        }
    }

    @GetMapping("/lb")
    public String getPaymentLb(){
        return  serverPort;
    }

    @GetMapping("/fegin/timeout")
    public String getTimeout(){
        System.out.println("*****paymentFeignTimeOut from port: "+serverPort);
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return  serverPort;
    }
}
