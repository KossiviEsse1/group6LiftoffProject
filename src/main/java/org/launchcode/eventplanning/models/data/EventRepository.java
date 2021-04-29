package org.launchcode.eventplanning.models.data;

import org.launchcode.eventplanning.models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
    /*@Query(value = "SELECT c FROM Event c WHERE c.name LIKE '%' || :keyword || '%'"
            + " OR c.name LIKE '%' || :keyword || '%'"
            + " OR c.location LIKE '%' || :keyword || '%'"
            + " OR c.description LIKE '%' || :keyword || '%'"
    )
    public List<Event> search(@Param("keyword") String keyword);*/
}
