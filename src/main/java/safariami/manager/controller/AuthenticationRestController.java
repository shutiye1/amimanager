package safariami.manager.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

import safariami.manager.security.jwt.JWTFilter;
import safariami.manager.security.jwt.TokenProvider;
import safariami.manager.util.error.ResourceNotFoundException;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping(path="/security")
public class AuthenticationRestController {

   private final TokenProvider tokenProvider;

   private final AuthenticationManagerBuilder authenticationManagerBuilder;

   public AuthenticationRestController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
      this.tokenProvider = tokenProvider;
      this.authenticationManagerBuilder = authenticationManagerBuilder;
   }

   @PostMapping("/auth")
   public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginDto loginDto) {

      UsernamePasswordAuthenticationToken authenticationToken =
         new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

      Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
      String jwt = tokenProvider.createToken(authentication, rememberMe);

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

      return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
   }
   
   @GetMapping("/refresh")
   public ResponseEntity<JWTToken> getRefreshToken(@RequestHeader(value=HttpHeaders.AUTHORIZATION) String authToken) {
	   if(this.tokenProvider.validateToken(authToken)) {
		   new ResourceNotFoundException("Invalid token");
	   }
	   
	   Authentication authentication = this.tokenProvider.getAuthentication(authToken);
	   String jwt = tokenProvider.createToken(authentication, false);

	   HttpHeaders httpHeaders = new HttpHeaders();
	   httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

	   return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
   }


   /**
    * Object to return as body in JWT Authentication.
    */
   static class JWTToken {

      private String idToken;

      JWTToken(String idToken) {
         this.idToken = idToken;
      }

      @JsonProperty("token")
      String getIdToken() {
         return idToken;
      }

      void setIdToken(String idToken) {
         this.idToken = idToken;
      }
   }
}
