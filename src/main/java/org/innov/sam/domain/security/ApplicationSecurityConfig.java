//package org.innov.sam.domain.security;
//
//import org.innov.sam.domain.User;
//import org.innov.sam.domain.repository.UserRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import static org.innov.sam.domain.security.ApplicationUserRole.Analyste;
//import static org.innov.sam.domain.security.ApplicationUserRole.Admin;
//
//
//@Configuration
//@EnableWebSecurity
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final PasswordEncoder passwordEncoder ;
//    private UserRepository usersRepository ;
//
//    private final Logger log = LoggerFactory.getLogger(ApplicationSecurityConfig.class);
//
//
//
//
//    @Autowired
//    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, UserRepository usersRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.usersRepository = usersRepository;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception{
//
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/api/users/**","/deleteuser/**").hasRole(Admin.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }
//
//
//
////    @Bean
////    protected UserDetailsService userDetailsService(String name){
////        log.debug("Authenticating {}", name);
////
////
////            Users samUser = new Users();
////            samUser = usersRepository.findOneByName(name);
////
////            UserDetails samUser1 = User.builder()
////                    .username(samUser.getName())
////                    .password(samUser.getPassword())
////                    .roles(samUser.getAdmin())
////                    .build();
////
////
////            return new InMemoryUserDetailsManager(samUser1);
////
////    }
////       @Override
////       @Bean
//////       protected UserDetailsService userDetailsService(){
//////        log.debug("Authenticating {}");
//////
//////            UserDetails samUser1 = User.builder()
//////                    .username("Salah")
//////                    .password(passwordEncoder.encode("12345"))
//////                    .roles(Analyste.name())
//////                    .build();
//////
//////            UserDetails samUser2 = User.builder()
//////                    .username("Bastami")
//////                    .password(passwordEncoder.encode("12345"))
//////                    .roles(Admin.name())
//////                    .build();
////
////
////            return new InMemoryUserDetailsManager(samUser1,samUser2);
////
////    }
//
//}
