package com.example.smartParkingAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartParkingApiApplication {

	public static void main(String[] args) {
//		PostgresConnection.initialize();
		
		SpringApplication.run(SmartParkingApiApplication.class, args);
	}
}
