package org.innov.sam.domain.service;

import org.innov.sam.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    public Iterable<User> findAll();

    public Page<User> findAll(Pageable pageable);

    public Optional<User> findById(Long idUser);

    public User save(User newUser);

    public User paryialUpdate(User updateUser);

    public User updateEtat(String nameUser);
    public User updatePassword(User userToChnagePassword ,String password);
    public User updateRole(String nameUser);

    public void deleteById(Long idUser);

    public User getUser (Long idUser);
    public Boolean checkNullOrEmpty(String tocheck);
    public Boolean checkNullOrEmpty(Boolean tocheck);
}
