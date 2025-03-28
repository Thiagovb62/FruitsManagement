package com.thiago.fruitmanagementsystem.Config;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {

    @Value("${paypal.client.id}")
    public String PAYPAL_CLIENT_ID;
    @Value("${paypal.client.secret}")
    public String PAYPAL_CLIENT_SECRET;
    @Value("${paypal.mode}")
    public String PAYPAL_MODE;


    @Bean
    public APIContext apiContext() {
        return new APIContext(PAYPAL_CLIENT_ID, PAYPAL_CLIENT_SECRET, PAYPAL_MODE);
    }
}