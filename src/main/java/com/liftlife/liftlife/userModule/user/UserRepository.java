package com.liftlife.liftlife.userModule.user;

import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
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
            throw new UserNotFoundException("User with email: " + email + " is not registered");
        else if(users.size() > 1)
            throw new UserNotFoundException("Multiple users with email : " + email + " was found");
        else return users.get(0);
    }

    public boolean isPresent(String email) {
        List<User> users = super.findByField("email", email);
        if(users.isEmpty())
            return false;
        else return true;
    }

}

