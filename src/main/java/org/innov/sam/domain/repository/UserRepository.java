package org.innov.sam.domain.repository;

import org.innov.sam.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByName(String name);
    Optional<User> findOneByEmail(String email);
    Page<User> findAllByIdNotNullAndActiveIsTrue(Pageable pageable);
    Page<User> findAllByIdNotNullAndAdminIsTrue(Pageable pageable);

    @Query("SELECT p FROM User p WHERE p.id = ?1")
    public User getUser (Long idUser);




}
