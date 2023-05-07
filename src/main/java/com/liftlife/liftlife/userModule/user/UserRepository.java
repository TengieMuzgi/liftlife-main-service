package com.liftlife.liftlife.userModule.user;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.util.exception.handler.UserNotFound;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class UserRepository extends FirestoreRepositoryTemplate<User> {
    public UserRepository() {
        super(User.class);
    }

    public User findByEmail(String email) {
        List<User> users = super.findByField("email", email);
        if(users.isEmpty())
            throw new UserNotFound("User with email: " + email + " is not registered");
        else if(users.size() > 1)
            throw new UserNotFound("Multiple users with email : " + email + " was found");
        else return users.get(0);
    }


}

