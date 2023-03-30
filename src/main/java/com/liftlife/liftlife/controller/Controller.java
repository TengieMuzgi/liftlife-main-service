package com.liftlife.liftlife.controller;

import com.google.gson.Gson;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/saveExample")
    public void saveExampleString() throws ExecutionException, InterruptedException {
        FirestoreRepositoryTemplate firestoreRepositoryTemplate = new FirestoreRepositoryTemplate("users");
        
    }
}
