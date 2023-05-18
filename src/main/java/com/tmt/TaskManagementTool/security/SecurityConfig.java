package com.tmt.TaskManagementTool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.tmt.TaskManagementTool.models.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

     @Autowired
    private AuthenticationProvider loginAuthenticationProvider;

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;
    


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
                                 AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        //authorize.anyRequest().authenticated()
                        authorize.requestMatchers(HttpMethod.POST, "/api/**").permitAll()
                                .requestMatchers("/api/v1/**").permitAll()
                                .anyRequest().authenticated()

                );*/

        
         http.csrf().disable()
                .authorizeHttpRequests()
                // make sure to grant access to any login page you are forwarding to
                .requestMatchers("/api/v1/login").permitAll()
                .and()
                .authenticationProvider(loginAuthenticationProvider)
                .formLogin().loginPage("/api/v1/login").successHandler(loginSuccessHandler)
                .and()
                .logout().logoutUrl("/ap1/v1/logout").logoutSuccessUrl("/api/v1/").deleteCookies("JSESSIONID")
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/api/v1/login")
                .maxSessionsPreventsLogin(false)
                .and()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/api/v1/login");
        

        return http.build();
    }

    @Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
