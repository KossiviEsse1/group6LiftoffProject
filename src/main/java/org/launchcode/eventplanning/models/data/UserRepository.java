package org.launchcode.eventplanning.models.data;

import org.launchcode.eventplanning.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    @Override
    default <S extends User> S save(S entity) {
        return null;
    }

    @Override
    Optional<User> findById(Integer integer);

    @Override
    Iterable<User> findAll();
}


