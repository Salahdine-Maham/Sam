package org.innov.sam.domain.rest;



import org.innov.sam.domain.User;
import org.innov.sam.domain.repository.UserRepository;
import org.innov.sam.domain.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@RestController
@RequestMapping("/api/user")
public class UsersResource {

    private final Logger log = LoggerFactory.getLogger(UsersResource.class);

    private final UserServiceImpl usersService;
    private final UserRepository usersRepository;


    public UsersResource(UserServiceImpl usersService, UserRepository usersRepository) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
    }



   // Create  a new User
    @PostMapping
    public ResponseEntity<?> create(@RequestBody User newUser){
        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/user")
                .toUriString());
        return ResponseEntity.created(uri).body(usersService.save(newUser));
    }


    // read an  User
    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value ="id") Long idUser){
        Optional<User> oUser = usersRepository.findById(idUser);
        if(!oUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(oUser);
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value = "id") Long idUser) throws URISyntaxException
    {
        log.debug("REST request to update User : {}, {}", idUser, userDetails);

        Optional<User> userToUpdate = usersRepository.findById(idUser);

        if(!userToUpdate.isPresent()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.paryialUpdate(userDetails));


    }





    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long idUser){

        // Todo we must check that the user can't delete his self

        if(!usersRepository.findById(idUser).isPresent()){
            return ResponseEntity.notFound().build();
        }

        usersRepository.deleteById(idUser);
        return  ResponseEntity.ok().build();
    }


 // read all users
    @GetMapping("/all")
    public List<User> readAll(){

        List<User> usersList = StreamSupport
                .stream( usersService.findAll().spliterator(), false)
                .collect(Collectors.toList());

        return  usersList;
    }



}
