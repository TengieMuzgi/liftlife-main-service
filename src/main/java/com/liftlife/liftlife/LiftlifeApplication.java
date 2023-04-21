package com.liftlife.liftlife;

import com.liftlife.liftlife.database.FirestoreMapper;
import com.liftlife.liftlife.database.FirestoreRepositoryTemplate;
import com.liftlife.liftlife.exercise.Exercise;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LiftlifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiftlifeApplication.class, args);
	}

	
	
}
