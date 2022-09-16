package tn.esprit.spring.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtTokenUtil {
	public static final long JWT_TOKEN_VALIDITY = 10 * 60 * 60 * 1000;
	
	private String secret = "ESPRIT";

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	public List<GrantedAuthority> getAuthorityFromToken(String token) {
		
		
		List<Map<String, String>> roles =  getAllClaimsFromToken(token).get("roles", List.class);

		
		List<GrantedAuthority> authorities = new ArrayList<>();
		roles.forEach(role ->

		{
			
			authorities.add(new SimpleGrantedAuthority(role.get("authority")));
		});

		return authorities ;
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {

		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {

		String token = "";
		token += Jwts.builder().setSubject(subject)
				.addClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)) // in milliseconds
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
		return token;
	}

//	public Boolean validateToken(String token, UserDetails userDetails) {
//		final String username = getUsernameFromToken(token);
//		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {

		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", authorities);
		return doGenerateToken(claims, username);
	}
}