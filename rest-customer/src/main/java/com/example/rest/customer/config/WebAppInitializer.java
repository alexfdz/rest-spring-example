package com.example.rest.customer.config;

import com.example.rest.commons.config.CommonWebAppInitializer;

public class WebAppInitializer extends CommonWebAppInitializer {

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class };
    }
}
