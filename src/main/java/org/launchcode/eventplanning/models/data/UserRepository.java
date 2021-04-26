package org.launchcode.eventplanning.models.data;

import org.launchcode.eventplanning.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}

