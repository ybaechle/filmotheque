package fr.eni.filmotheque.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/", "/films", "/filmotheque", "/film").permitAll()
				.requestMatchers("/filmLogout", "/login").permitAll()
				.requestMatchers("/css/**", "/images/**").permitAll()
				.requestMatchers("/genres").hasRole("ADMIN")
				.requestMatchers("/ajoutFilm")
					.hasAnyRole("MEMBRE", "ADMIN")
				.anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/", true))
				.logout((logout) -> logout.clearAuthentication(true).invalidateHttpSession(true)
						.deleteCookies("JSESSIONID").logoutSuccessUrl("/filmLogout")
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());

		return http.build();
	}

	@Bean
	public PasswordEncoder encoder() {
		// Production :
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// Dev/test
		 //return NoOpPasswordEncoder.getInstance();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetails user =
//			 User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("password")
//				.roles( "ADMIN")
//				.build();
//
//		return userDetailsService();
//	}
}