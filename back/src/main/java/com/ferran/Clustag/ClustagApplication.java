package com.ferran.Clustag;

import com.ferran.Clustag.models.TaskModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;

@SpringBootApplication
@EnableScheduling
public class ClustagApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClustagApplication.class, args);
	}

}
