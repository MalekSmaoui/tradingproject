	package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.config.JwtTokenUtil;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.response.JwtRequest;
import tn.esprit.spring.response.JwtResponse;
import tn.esprit.spring.services.IUserService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private IUserService usersService;

	@PostMapping("/authenticate")
	public JwtResponse createAuthenticationToken(@RequestBody JwtRequest user) throws Exception {
	
		Authentication auth = authenticate(user.getUsername(), user.getPassword());
		final User userDetails = (User) usersService.retrieveUserByUsername(user.getUsername());
		final String token = jwtTokenUtil.generateToken(auth.getName(), auth.getAuthorities());
		return new JwtResponse(token, userDetails);
	}

	private Authentication authenticate(String username, String password) throws Exception {
		try {
			Authentication auth = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			return auth;
		} catch (InternalAuthenticationServiceException e) {
			throw new InternalAuthenticationServiceException("INVALID_USERNAME", e);
		}

		catch (DisabledException e) {
			throw new DisabledException("USER_DISABLED", e);
		}

		catch (BadCredentialsException e) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", e);
		}
	}
	@GetMapping("/username")
	public int getAuthentication() {
		String username= SecurityContextHolder.getContext().getAuthentication().getName();
         User u= usersService.retrieveUserByUsername(username);
            return u.getIdUser();
	}
}
