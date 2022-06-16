package org.innov.sam;

import org.innov.sam.domain.User;
import org.innov.sam.domain.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SamApplication  {



    public static void main(String[] args) {
        SpringApplication.run(SamApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner run(UserServiceImpl userService ){
        return  args -> {

            userService.save( new User( null,"Houda","s","s","s" ,true,true));
            userService.save( new User( null,"Salah","s","s","s" ,true,false));


        };}
//    @Override
//    public void run(String... args) throws Exception {
//
//       userService.save( new User( null,"Houda","s","s","s" ,true,true));
//        userService.save( new User( null,"Houda","s","s","s" ,true,false));
////        usersService.createUser( new Users( "b","s","s","s" ,true,"analyste"));
//        usersService.createUser( new Users( "c","s","s","s" ,true,"analyste"));
//        usersService.createUser( new Users( "d","s","s","s" ,true,"admin"));
//        usersService.createUser( new Users( "e","s","s","s" ,true,"admin"));
//        usersService.createUser( new Users(2L, "s","s","s","s" ,true,"analyste"));

//    }
}
