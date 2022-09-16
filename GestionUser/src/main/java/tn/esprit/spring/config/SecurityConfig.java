package tn.esprit.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tn.esprit.spring.config.*;
import tn.esprit.spring.security.JwtUserDetailsService;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtUserDetailsService userService;
	@Autowired
	private JwtAuthorizationFilter jwtAuthorizationFilter;

	@Autowired
	private Http401UnauthorizedEntryPoint authenticationEntryPoint;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers("/v2/api-docs",
                 "/configuration/ui",
                 "/swagger-resources/**",
                 "/configuration/security",
                 "/swagger-ui.html",
                 "/swagger-ui/**",
                 "/webjars/**");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests().antMatchers("/authenticate").permitAll();
		http.authorizeRequests().antMatchers("/username").permitAll();
		


		http.addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

		http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

		http.cors();
	}

	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

//	@Bean
//	public CorsFilter corsFilter() {
//
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		final CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowCredentials(true);
//		configuration.addAllowedOrigin("*");
//		configuration.addAllowedHeader("*");
//		configuration.addAllowedMethod("*");
//		configuration.addExposedHeader("Authorization");
//		source.registerCorsConfiguration("/**", configuration);
//		return new CorsFilter(source);
//	}

}
