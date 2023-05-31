package com.liftlife.liftlife.userModule.user.utils;

import com.liftlife.liftlife.userModule.user.User;
import com.liftlife.liftlife.util.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.util.exception.NotFoundException;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.io.NotActiveException;
import java.util.List;

@Repository
@DependsOn("firestoreConnector")
public class RegistrationTokenRepository extends FirestoreRepositoryTemplate<RegistrationToken> {

    public RegistrationTokenRepository() {
        super(RegistrationToken.class);
    }

    public RegistrationToken findByToken(String token) {
        List<RegistrationToken> tokens = super.findByField("token", token);
        if(tokens.isEmpty())
            throw new NotFoundException("Token: " + token + " not found");
        else if(tokens.size() > 1)
            throw new NotFoundException("Multiple tokens : " + token + " was found");
        else return tokens.get(0);
    }

    public boolean isPresent(String token) {
        List<RegistrationToken> tokens = super.findByField("token", token);
        if(tokens.isEmpty())
            return false;
        else return true;
    }
}
