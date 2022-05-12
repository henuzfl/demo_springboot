package com.zfl.demo.infrastructure.auth.configuration;

import com.zfl.demo.infrastructure.auth.security.UserDetailServiceImpl;
import com.zfl.demo.infrastructure.auth.security.jwt.JWTConfigurer;
import com.zfl.demo.infrastructure.auth.security.jwt.TokenProvider;
import com.zfl.demo.infrastructure.auth.service.JwtAccessDeniedHandler;
import com.zfl.demo.infrastructure.auth.service.JwtAuthenticationEntryPoint;
import com.zfl.demo.infrastructure.configuration.CustomConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CustomConfiguration.class)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailService;

    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomConfiguration customConfiguration;

    public WebSecurityConfiguration(UserDetailServiceImpl userDetailService,
                                    TokenProvider tokenProvider,
                                    CorsFilter corsFilter,
                                    JwtAuthenticationEntryPoint authenticationErrorHandler,
                                    JwtAccessDeniedHandler jwtAccessDeniedHandler, CustomConfiguration customConfiguration) {
        this.userDetailService = userDetailService;
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.authenticationErrorHandler = authenticationErrorHandler;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.customConfiguration = customConfiguration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and().csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .logout().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .apply(securityConfigurerAdapter());
    }

    @Override
    public void configure(WebSecurity web) {
        WebSecurity and = web.ignoring().and();
        customConfiguration.getIgnores().stream().forEach(ignore -> and.ignoring().antMatchers(ignore));
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(encoder());
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
