package com.forezp.providerA;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName providerAApplication
 * @Description 功能说明：
 * @Author 刘松
 * @Date 2018/5/3 11:27
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@RestController

public class providerAApplication {

    @Value("${server.port}")
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(providerAApplication.class, args);
    }

    @RequestMapping(value = "/hi")
    public String hi(HttpServletRequest request){
        String url = "http://10.14.125.195:8000/testB/hi";
        String gray = request.getParameter("version");
        if("gray".equals(gray)){
            url = url +"?version=gray";
        }
        else if(gray == null || "".equals(gray)){

        }
        else{
            url = url +"?version=gray1";
        }

        String result = restTemplate.getForObject(url, String.class);
        return "我是providerA,我的端口号是："+port+",我调用的方法是" + result;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
