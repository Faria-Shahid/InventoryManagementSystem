Inventory Management System
===========================

Overview
--------
This repository contains a two-phase development of an Inventory Management System built as part of an academic project.
The system is designed to manage product inventory efficiently, with enhancements introduced in each phase for improved usability and scalability.

Project Structure
-----------------
<pre> FariaShahid_NED_2026/ ├── v1/ │ └── KiryanaStore/ # CLI-based prototype ├── v2/ │ └── InventoryManagementSystem/ # RESTful scalable system └── FariaShahid_NED_2026.pdf # Documentation </pre>

Phase 1 - CLI Prototype
-----------------------
- A command-line interface (CLI) application named KiryanaStore.
- Demonstrates the core functionality of an inventory management system.
- Includes features like adding, removing, and updating inventory items.
- Built for validating the core logic in a minimal environment.

Phase 2 - RESTful Web Application
---------------------------------
- Built for scalability using modern software design principles.
- Implements:
  - REST controllers for handling HTTP requests.
  - Repository pattern for database interaction.
  - DTOs (Data Transfer Objects) for separating persistence and representation layers.
- Developed with extensibility and clean architecture in mind.

How to Run
----------

Phase 1 - CLI
1. Navigate to v1/KiryanaStore/
2. Compile and run the Java classes using your IDE or the command line.

Phase 2 - REST App
1. Navigate to v2/InventoryManagementSystem/
2. Import the project into an IDE like IntelliJ or Eclipse (Spring Boot compatible).
3. Run the application as a Spring Boot app.
4. Use tools like Postman to interact with the REST endpoints.
