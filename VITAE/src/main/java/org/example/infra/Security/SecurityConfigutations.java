package org.example.infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigutations {
    @Autowired
    SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.cors(Customizer.withDefaults()).csrf
                (crsf -> crsf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize  -> authorize
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                                .requestMatchers("/Api/Agenda/dowload/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/Api/Agenda/buscarDoadorAgendado/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/Api/hospital/detalhes/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/Api/Endereco/mapa").permitAll()
                                .requestMatchers(HttpMethod.GET, "/Api/Doacao/Rank").permitAll()
                                .requestMatchers(HttpMethod.GET, "/Api/Doacao/Posicao/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/Api/auth/login").permitAll()
                                .requestMatchers(HttpMethod.GET,"/Api/usuario/detalhes/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/Api/usuario/ler").permitAll()
                                .requestMatchers(HttpMethod.GET,"/Api/usuario/arquivoTxT").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/Api/usuario/detalhesUser/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/Api/Caracteristicas/detalhes/**").permitAll()
                                .requestMatchers(HttpMethod.PUT,"/Api/Endereco/detalhes/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/Api/usuario/register/lista").permitAll()
                                .requestMatchers(HttpMethod.POST,"/Api/usuario/register").permitAll().
                        anyRequest().authenticated()

                )

                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration  configAutencacao) throws Exception {
        return configAutencacao.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
