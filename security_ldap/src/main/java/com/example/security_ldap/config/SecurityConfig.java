package com.example.security_ldap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 🔹 1. Define LDAP Connection Source
    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(
                Collections.singletonList("ldap://localhost:10389"),
                "dc=example,dc=com"
        );
    }

    // 🔹 2. Define AuthenticationManager (Bind + Group Roles)
    @Bean
    public AuthenticationManager authenticationManager(DefaultSpringSecurityContextSource contextSource) {

        // --- User search
        var userSearch = new FilterBasedLdapUserSearch(
                "ou=people",        // base
                "(uid={0})",        // search filter
                contextSource
        );

        // --- Bind authenticator
        var authenticator = new BindAuthenticator(contextSource);
        authenticator.setUserSearch(userSearch);

        // --- Group/role mapping
        var authoritiesPopulator = new DefaultLdapAuthoritiesPopulator(contextSource, "ou=groups");
        authoritiesPopulator.setGroupSearchFilter("(member={0})");
        authoritiesPopulator.setGroupRoleAttribute("cn");

        // --- Role name normalization
        var authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setPrefix("ROLE_");

        // --- Combine authenticator + authorities
        var provider = new LdapAuthenticationProvider(authenticator, authoritiesPopulator);
        provider.setAuthoritiesMapper(authorityMapper);

        return new ProviderManager(List.of(provider));
    }

    // 🔹 3. Security Rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMINS")
                        .requestMatchers("/developer/**").hasRole("DEVELOPERS")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.permitAll())
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
