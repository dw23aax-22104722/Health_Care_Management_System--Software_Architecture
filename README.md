Healthcare Management System
Overview

This project is a Healthcare Management System developed in Java for a Software Architecture coursework assignment. The application allows users to manage healthcare records through a graphical user interface built with Java Swing.

The system follows the Model-View-Controller (MVC) architectural pattern and implements the Singleton design pattern for referral management. Data is loaded from and saved to CSV files, providing persistent storage without the use of a database.

Features

1.) Patient Management

Manage patient records containing:

- Patient ID
- Name
- Date of Birth

Operations:

- Add patient
- Edit patient
- Delete patient
- View patient records

2.) Clinician Management

Manage clinician records containing:

- Clinician ID
- Name
- Role

Operations:

- Add clinician
- Edit clinician
- Delete clinician
- View clinician records

3.) Appointment Management

Manage appointment records containing:

- Appointment ID
- Patient ID
- Clinician ID
- Date

Operations:

- Add appointment
- Edit appointment
- Delete appointment
- View appointments

Validation:

a.) Patient must exist
b.) Clinician must exist
c.) Only clinicians with the role GP can manage appointments

4.) Prescription Management

Manage prescription records containing:

- Prescription ID
- Patient ID
- Clinician ID
- Medication
- Status

Operations:

- Add prescription
- Edit prescription
- Delete prescription
- View prescriptions

Validation:

a.) Patient must exist
b.) Clinician must exist
c.) Status must be either Issued or Collected

5.) Referral Management

Manage referral records containing:

- Referral ID
- Patient ID
- Referring Clinician ID
- Receiving Clinician ID

Operations:

- Add referral
- Edit referral
- Delete referral
- View referrals

Validation:

a.) Patient must exist
b.) Both clinicians must exist
c.) A clinician cannot refer a patient to themselves

Design Patterns

MVC Architecture

The application is organised using the Model-View-Controller architectural pattern.

i.) Model

Responsible for storing and managing healthcare data.

Classes:

- Patient
- Clinician
- Appointment
- Prescription
- Referral
- ReferralManager
- CSVUtil

ii.) View

Implemented using Java Swing.

Class:

- HealthcareView

The graphical interface provides separate tabs for:

- Patients
- Appointments
- Prescriptions
- Referrals
- Clinicians

iii.) Controller

Responsible for handling user actions, validation, and communication between the view and model.

Class:

- HealthcareController

Singleton Pattern

The coursework specification required the use of the Singleton design pattern within the referral management functionality.

This is implemented through the ReferralManager class, ensuring that only one referral manager instance exists throughout the application.

Example:

ReferralManager referralManager = ReferralManager.getInstance();

Data Storage

The application uses CSV files as its data source and storage mechanism.

Files used:

- patients.csv
- clinicians.csv
- appointments.csv
- prescriptions.csv
- referrals.csv

When the application starts, records are loaded from these files.

When the application closes, any changes made through the GUI are automatically saved back to the corresponding CSV files.

Graphical User Interface

The system uses a tabbed Java Swing interface.

Each tab contains:

- A table displaying records
- Add button
- Edit button
- Delete button

Available tabs:

- Patients
- Appointments
- Prescriptions
- Referrals
- Clinicians

Project Structure

src - under src: 

i.) Model

- Appointment.java
- Clinician.java
- CSVUtil.java
- Patient.java
- Prescription.java
- Referral.java
- ReferralManager.java

ii.) Controller
- HealthcareController.java

iii.) View
- HealthcareView.java

iv.) Main Application
- HealthcareApplication.java

Running the Application:
- Ensure the CSV files are located in the project directory.
- Compile the project.
- Run HealthcareApplication.
- The Healthcare Management System window will open.

Coursework Requirements Addressed
- Java implementation
- Java Swing GUI
- MVC architecture
- Singleton design pattern
- CSV file storage
- CRUD operations
- Data validation
- Object-oriented design principles
- Persistent record management
