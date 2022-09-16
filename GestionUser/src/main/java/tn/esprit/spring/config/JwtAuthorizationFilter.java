package tn.esprit.spring.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtAuthorizationFilter extends OncePerRequestFilter {


	@Value("${jwt.secret}")
	private String signinKey;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestHeader = request.getHeader("Authorization");

		// 2. validate the header and check the prefix
		if (requestHeader == null || !requestHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response); // If not valid, go to the next filter.
			return;
		}
		// If there is no token provided and hence the user won't be authenticated.
		// It's Ok. Maybe the user accessing a public path or asking for a token.

		// All secured paths that needs a token are already defined and secured in
		// config class.
		// And If user tried to access without access token, then he won't be
		// authenticated and an exception will be thrown.

		// 3. Get the token
		String token = requestHeader.replace("Bearer ", "");

		try { // exceptions might be thrown in creating the claims if for example the token is
				// expired

		

			String username = jwtTokenUtil.getUsernameFromToken(token);
			if (username != null) {
				

				List<GrantedAuthority> authorities = jwtTokenUtil.getAuthorityFromToken(token);
				

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						authorities);

				// 6. Authenticate the user
				// Now, user is authenticated
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		} catch (Exception e) {
			// In case of failure. Make sure it's clear; so guarantee user won't be
			// authenticated
			e.printStackTrace();
			SecurityContextHolder.clearContext();
		}

		// go to the next filter in the filter chain
		filterChain.doFilter(request, response);

	}

}
