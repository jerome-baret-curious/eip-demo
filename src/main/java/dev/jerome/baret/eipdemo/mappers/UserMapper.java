package dev.jerome.baret.eipdemo.mappers;

import dev.jerome.baret.eipdemo.entities.InputData;
import org.openapitools.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User map(InputData entity) {
        User dto = new User();
        return dto.id(entity.getId())
                .done(entity.getDone())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName());
    }
}
