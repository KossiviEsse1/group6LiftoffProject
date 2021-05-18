package org.launchcode.eventplanning.models.data;

import org.launchcode.eventplanning.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventUserRepository extends CrudRepository<User, Integer> {
}
