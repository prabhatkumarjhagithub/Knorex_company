package knorex.Company.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class CompanyAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyAssignmentApplication.class, args);
	}

	  try {
		// Initialize parking lot with 2 floors, 5 spaces each, and hourly cost strategy in INR
		CostStrategy costStrategy = new HourlyCostStrategy(Currency.INR);
		ParkingLot parkingLot = new ParkingLot(2, 5, costStrategy);

		System.out.println("Welcome to Parking System");
		System.out.println("Initialized parking lot with 2 floors and 5 spaces per floor");


		Vehicle car1 = new Vehicle("KA01HH1234", VehicleType.CAR, "Red");
		String token1 = parkingLot.parkVehicle(car1);
		System.out.println("\nParked car 1");
		System.out.println("Registration Number: " + car1.getRegistrationNumber());
		System.out.println("Token: " + token1);

		// Park second vehicle
		Vehicle car2 = new Vehicle("KA01HH5678", VehicleType.CAR, "Blue");
		String token2 = parkingLot.parkVehicle(car2);
		System.out.println("\nParked car 2");
		System.out.println("Registration Number: " + car2.getRegistrationNumber());
		System.out.println("Token: " + token2);

		// Display current status
		parkingLot.displayStatus();

		
		try {
			Vehicle car3 = new Vehicle("KA01HH9012", VehicleType.CAR, "Green");
			parkingLot.parkVehicle(car3);
		} catch (ParkingException e) {
			System.out.println("\nError: " + e.getMessage());
		}

		// Remove a vehicle and calculate cost
		LocalDateTime exitTime = LocalDateTime.now().plusHours(3);
		double cost = parkingLot.removeVehicle(token1, exitTime);
		System.out.println("\nRemoving car 1");
		System.out.println("Parking duration: 3 hours");
		System.out.println("Parking cost: " + costStrategy.formatCost(cost));

		// Display final status
		parkingLot.displayStatus();

	} catch (ParkingException e) {
		System.out.println("Error: " + e.getMessage());
	}
}
