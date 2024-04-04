package br.com.horarios.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth
				//Qualquer tipo de permissao consegue acessar esse @Controller
				.requestMatchers("/signin", "/signup").permitAll()
				.requestMatchers("/preferencia").hasAnyAuthority("professor", "coordenador_curso")
				//Quem possuir algum dos perfis pode acessar o @controller
				.requestMatchers("/disponibilidade").hasAnyAuthority("professor", "coordenador_curso")
				.requestMatchers("/docente").hasAnyAuthority("administrador")
				//Qualquer requisiçao ao @controller o usuario precisa estar autenticado
				.anyRequest().authenticated()
				)
				 .formLogin(formLogin -> formLogin
						 //Direciona para esse @controller quando o login estar correto
						 .defaultSuccessUrl("/principal", true)
						 .permitAll()
						 )
				 .rememberMe(rememberMe -> rememberMe.key("AbcdEfghIjkl..."))
				 .logout(logout -> logout.logoutUrl("/signout").permitAll());
		
		
		return http.build();
	}
		
		@Autowired
		public void configureGlobal(AuthenticationManagerBuilder auth)
		throws Exception {
			//Server de exemplo para gerar uma senha criptografada
			BCryptPasswordEncoder b = new BCryptPasswordEncoder();
			System.out.print(b.encode("123456"));
			//Cripitografa a senha para salvar no banco de dados
			auth.userDetailsService(userDetailServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
		
		
		
		
	}

}
