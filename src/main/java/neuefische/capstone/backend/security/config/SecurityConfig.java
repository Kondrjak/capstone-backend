package neuefische.capstone.backend.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final Filter jwtAuthFilter;
    /**
     * Security
     * managed by the Spring IoC container
     * @annotation @Autowired - instantiation of autowired components is managed by the IoC container
     * @param jwtAuthFilter - only pass the filter with valid (jwt) token in bearer-auth-http-header
     */
    @Autowired
    SecurityConfig(Filter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * Password encoder bean
     * @annotation @Bean - instantiation of spring beans is managed by the IoC container
     * @return pwdEncoder - a PasswordEncoder object with methods encode, matches and upgradeEncoding
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication manager bean
     * @return @Bean - instantiation is managed by the Spring IoC container
     * @throws Exception - if authentication is rejected
     */
    @Bean
    public AuthenticationManager AuthManager() {
        try{
            return super.authenticationManagerBean();
        }catch (Exception e){
            throw new InsufficientAuthenticationException("authentication rejected");
        }
    }


    /**
     * Override parent class method to configure {@link WebSecurity}.
     * For example, if you wish to ignore certain requests.
     *
     * Endpoints specified in this method will be ignored by Spring Security, meaning it
     * will not protect them from CSRF, XSS, Clickjacking, and so on.
     *
     * Instead, if you want to protect endpoints against common vulnerabilities,
     * then see {@link #configure(HttpSecurity)} and the
     * {@link HttpSecurity#authorizeRequests} configuration method.
     *
     * @param http - the httpSecurity, that is configured
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/health").permitAll()
                .antMatchers("/**").authenticated()
                .and().addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}