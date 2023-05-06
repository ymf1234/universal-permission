package com.github.ymf1234.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // 开启springSecurity的默认行为
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
}
