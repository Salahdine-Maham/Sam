package org.innov.sam.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.innov.sam.domain.User;
import org.innov.sam.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl  implements  UserService , UserDetailsService {


    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository usersRepository ;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        return  usersRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long idUser) {
        return usersRepository.findById(idUser);
    }


    @Override
    @Transactional
    public User save(User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return usersRepository.save(newUser);
    }



    @Override
    public User paryialUpdate(User updateUser) {

        log.debug("Request to partially update User : {}", updateUser);

        User userToUpdate =  usersRepository.getUser(updateUser.getId());


        if (checkNullOrEmpty(updateUser.getName()) )  userToUpdate.setName(updateUser.getName());

        // check if the user name is Already used
        if (checkNullOrEmpty(updateUser.getFullname()) ){
            List<User> listUsers = usersRepository.findAll();
            Iterator<User> itr = listUsers.iterator();
            while( itr.hasNext()){
                if ( updateUser.getName() == itr.next().getName())
                    throw new UsernameAlreadyUsedException();
            }

            userToUpdate.setFullname(updateUser.getFullname());}

        if (checkNullOrEmpty(updateUser.getEmail()) )  userToUpdate.setEmail(updateUser.getEmail());
        if (checkNullOrEmpty(updateUser.getPassword()) ) userToUpdate.setPassword (updateUser.getPassword());
        if (checkNullOrEmpty(updateUser.getActive()))  userToUpdate.setActive(updateUser.getActive());
        if (checkNullOrEmpty(updateUser.getAdmin()))  userToUpdate.setAdmin(updateUser.getAdmin());



    usersRepository.save(userToUpdate);
return userToUpdate;
    }



    @Override
    public User updateEtat(String nameUser) {
        log.debug("Request to Update Etat Active or not : {}", nameUser);

        User userEtatUser =  usersRepository.findOneByName(nameUser);
        Boolean etat = userEtatUser.getActive();
        userEtatUser.setActive(!etat);

        return userEtatUser;
    }


    @Override
    public User updatePassword(User userToChnagePassword, String password) {
        log.debug("Request to Change Password of User : {}", userToChnagePassword);
        userToChnagePassword.setPassword(password);

        return userToChnagePassword;

    }

    @Override
    public User updateRole(String nameUser) {
        log.debug("Request to chnage Role user from Admin or Analyste: {}", nameUser);

        User userRoleUserUpdate =  usersRepository.findOneByName(nameUser);
        Boolean role = userRoleUserUpdate.getAdmin();
        userRoleUserUpdate.setAdmin(!role);

        return userRoleUserUpdate;
    }




    @Override
    @Transactional
    public void deleteById(Long idUser) {
        usersRepository.deleteById(idUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long idUser) {
        User fUser =  (User)usersRepository.getUser(idUser);
        return fUser;
    }


// Fonction to check Attribute For the parial Update

    public Boolean checkNullOrEmpty(String attributeToCheck ){
        if (attributeToCheck != null && !attributeToCheck.trim().isEmpty()){
            return true;
        }  else return false;

    }

    public Boolean checkNullOrEmpty(Boolean attributeToCheck ){
        if (attributeToCheck != null ){
            return true;
        }  else return false;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = usersRepository.findOneByName(username);

            if ( user == null){
                log.error("User not found in the database");
                throw new UsernameNotFoundException("User not found in the database");
            } else{
                log.error("User found in the database" , username);
            }

            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            if( user.getAdmin()) {
                authorities.add(new SimpleGrantedAuthority("ADMIN"));}else {
                authorities.add(new SimpleGrantedAuthority("ANALYSTE"));
            }

            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
        }

}




