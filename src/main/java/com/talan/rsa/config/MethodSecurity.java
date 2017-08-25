package com.talan.rsa.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@EnableGlobalMethodSecurity(securedEnabled=true)
public class MethodSecurity extends GlobalMethodSecurityConfiguration{

}
