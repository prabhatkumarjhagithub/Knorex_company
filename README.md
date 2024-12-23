The Parking Lot System is designed to efficiently manage vehicle ciated costs. The system supports various vehicle types and handles the management of parking spaces, including cost calculations based on hourly rates and ensuring that vehicles are parked and removed correctly.

Features
1. Vehicle Types
The system supports different of vehicles, including:
Car
Sports Car
Truck (requires 2 spaces)
Bus
Bike
Each vehicle type has specific space requirements, such as a k requiring 2 parking spaces.
2. Vehicle Class
The Vehicle class holds details about a vehicle:
Registration number

Color
Entry time
Unique token for identification
3. Parking Spaces
The VehicleSpace class represents individual parking spots.
Each parking space can be either:
Available
Occupied by a vehicle
The system tracks which spaces are occupied and which are free.
4. Floors
The parking lot is divided into multiple floors.
Each floor contains several parking spaces and may accommodate different vehicle types based on available spaces.
5. Cost Calculation
The HourlyCostStrategy class calculates the parking  vehicle and the time spent in the parking lot.
Costs vary based on the vehicle type (e.g., Bikes are cheaper than Trucks).
6. Parking Operations
Vehicles can be parked if there are available spaces.
The system checks for availability on each floond assigns a parking space accordingly.
Vehicles can be removed, and the system calculates the total parking fee based on the duration of stay.
