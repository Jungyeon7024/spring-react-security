package com.kh.springchap4.naverAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
		@Bean
		SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
			http.authorizeHttpRequests(authorizeHttpRequests->authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
			.oauth2Login(oauth2Login->oauth2Login.successHandler(new SimpleUrlAuthenticationSuccessHandler("/loginSuccess")));
			return http.build();		
			}
		// 추후 네이버 등록한 정보를 저장
		//InMemoryClientRegistrationRepository 등로하기 위한 공간
		//naverClientRegistration 
		 @Bean
		    public ClientRegistrationRepository clientRegistrationRepository() {
		        return new InMemoryClientRegistrationRepository(naverClientRegistration(),kakaoClientRegistration(),googleClientRegistration());
		    }
		 // 네이버 클라이언트의 등록 정보를 생성하는 메서드
		 // 클라이언트 아이디와 시크릿, 인증 후 리다이렉트 URL설정
		
		  //인증 통합 관리하는 매니저 
		   @Bean
		    public OAuth2AuthorizedClientManager authorizedClientManager(
		            ClientRegistrationRepository clientRegistrationRepository,
		            OAuth2AuthorizedClientRepository authorizedClientRepository) {
			   //클라이언트 인증처리
		        OAuth2AuthorizedClientProvider authorizedClientProvider =
		                OAuth2AuthorizedClientProviderBuilder.builder()
		                        .authorizationCode()
		                        .build();
		        //클라이언트 등록 정보와 인증된 클라이언트 저장소를 설정
		        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
		                new DefaultOAuth2AuthorizedClientManager(
		                        clientRegistrationRepository, authorizedClientRepository);
		        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

		        return authorizedClientManager;
		    }
		   public ClientRegistration naverClientRegistration() {
		        return ClientRegistration.withRegistrationId("naver")
		                .clientId("ZQJ47cEEgG2mZcx4r04A")
		                .clientSecret("TDjsUHt3oA")
		       		 // 네이버에서 인증하고나서 OAuth2 엔드포인트 설정
		                .redirectUri("http://localhost:8080/login/oauth2/code/naver")
		                .clientName("Naver")
		                .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
		                .tokenUri("https://nid.naver.com/oauth2.0/token")
		                .userInfoUri("https://openapi.naver.com/v1/nid/me")
		                .userNameAttributeName("response")
		                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
		                .build();   
		       
		    }
		   
		   public ClientRegistration googleClientRegistration() {
		        return ClientRegistration.withRegistrationId("google")
		                .clientId("664961366098-74ff769f5coups9d2jil81jv8i7t0q36.apps.googleusercontent.com")
		                .clientSecret("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")
		                .redirectUri("http://localhost:8080/login/oauth2/code/google")
		                .clientName("Google")
		                .authorizationUri("https://accounts.google.com/o/oauth2/auth")
		                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
		                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
		                .userNameAttributeName("sub")
		                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
		                .scope("openid", "profile", "email")
		                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
		                .build();
		    }
			public ClientRegistration kakaoClientRegistration() {
				return ClientRegistration.withRegistrationId("kakao")
						.clientId("4bec4086e3499d1d7569601f8c987f8d")
						.clientSecret("z0bNunQsk71ZbErkPJuEqgXuLit4F5br")
						.redirectUri("http://localhost:8080/login/oauth2/code/kakao")
		                .clientName("Kakao")
		                .authorizationUri("https://kauth.kakao.com/oauth/authorize")
		                .tokenUri("https://kauth.kakao.com/oauth/token")
		                .userInfoUri("https://kapi.kakao.com/v2/user/me")
		                .userNameAttributeName("id")
		                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
		                .scope("profile_nickname", "profile_image", "account_email") 
		                .build();
			}
		  
}	
