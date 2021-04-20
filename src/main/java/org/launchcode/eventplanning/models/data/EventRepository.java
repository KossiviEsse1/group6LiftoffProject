package org.launchcode.eventplanning.models.data;

import org.launchcode.eventplanning.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
