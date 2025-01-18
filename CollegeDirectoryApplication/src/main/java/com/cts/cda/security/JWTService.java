package com.cts.cda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.cda.entity.User;
import com.cts.cda.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

	@Value("${jwt.secret}")
	private String secretkey;
	@Autowired
	private UserRepository userRepo;

	public String generateToken(String username) {
		User user = userRepo.findByEmail(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
	    String role = user.getRole();
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
				.and()
				.signWith(getKey())
				.compact();
	}
	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
     //   System.out.println("extracted user name ", username);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
}
