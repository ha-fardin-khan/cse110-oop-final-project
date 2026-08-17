# Hospital Management System

This is a CSE110 OOP project based on the idea:

```text
Person (abstract)
 |-- Patient
 `-- Doctor implements Schedulable, Prescribable

Schedulable
Prescribable
Appointment
Prescription
HospitalManagementSystem
FileStorage
Custom exceptions
```

## UML Class Diagram

```mermaid
classDiagram
    class Person {
        <<abstract>>
        -String id
        -String name
        -int age
        -String phone
        +showInfo() void
    }

    class Patient {
        -String problem
        +showInfo() void
        +toFileString() String
    }

    class Doctor {
        -String specialization
        -ArrayList~String~ availableSlots
        +addSlot(String timeSlot) void
        +bookSlot(String timeSlot) void
        +releaseSlot(String timeSlot) void
        +hasSlot(String timeSlot) boolean
        +writePrescription(String prescriptionId, Appointment appointment, String medicine, String advice) Prescription
        +showInfo() void
    }

    class Schedulable {
        <<interface>>
        +addSlot(String timeSlot) void
        +bookSlot(String timeSlot) void
        +releaseSlot(String timeSlot) void
        +hasSlot(String timeSlot) boolean
    }

    class Prescribable {
        <<interface>>
        +writePrescription(String prescriptionId, Appointment appointment, String medicine, String advice) Prescription
    }

    class Appointment {
        -String appointmentId
        -Patient patient
        -Doctor doctor
        -String timeSlot
        -String status
        +cancel() void
        +complete() void
        +showAppointment() void
    }

    class Prescription {
        -String prescriptionId
        -Appointment appointment
        -String medicine
        -String advice
        +showPrescription() void
    }

    class HospitalManagementSystem {
        -ArrayList~Patient~ patients
        -ArrayList~Doctor~ doctors
        -ArrayList~Appointment~ appointments
        -ArrayList~Prescription~ prescriptions
        +addPatient(Patient patient) void
        +addDoctor(Doctor doctor) void
        +bookAppointment(String patientId, String doctorId, String timeSlot) Appointment
        +cancelAppointment(String appointmentId) void
        +completeAppointment(String appointmentId, String medicine, String advice) Prescription
    }

    class FileStorage {
        +saveAll(HospitalManagementSystem system, String folderPath) void
        +loadAll(String folderPath) HospitalManagementSystem
    }

    class SlotUnavailableException {
        <<checked exception>>
    }

    class PatientNotFoundException {
        <<checked exception>>
    }

    class DoctorNotFoundException {
        <<checked exception>>
    }

    class InvalidOperationException {
        <<unchecked exception>>
    }

    Person <|-- Patient
    Person <|-- Doctor
    Schedulable <|.. Doctor
    Prescribable <|.. Doctor

    HospitalManagementSystem "1" o-- "*" Patient : owns
    HospitalManagementSystem "1" o-- "*" Doctor : owns
    HospitalManagementSystem "1" o-- "*" Appointment : owns
    HospitalManagementSystem "1" o-- "*" Prescription : owns

    Appointment "*" --> "1" Patient : links
    Appointment "*" --> "1" Doctor : links
    Prescription "*" --> "1" Appointment : created from
    FileStorage ..> HospitalManagementSystem : saves and loads

    Doctor ..> SlotUnavailableException : throws
    HospitalManagementSystem ..> PatientNotFoundException : throws
    HospitalManagementSystem ..> DoctorNotFoundException : throws
    HospitalManagementSystem ..> InvalidOperationException : throws
```

## Main Classes

| Class | Purpose |
|---|---|
| `Person` | Abstract parent class for common person data |
| `Patient` | Stores patient information |
| `Doctor` | Stores doctor information and handles slots/prescriptions |
| `Appointment` | Links patient, doctor, and time slot |
| `Prescription` | Created after completing an appointment |
| `HospitalManagementSystem` | Manages all patients, doctors, appointments, and prescriptions |
| `FileStorage` | Saves and loads data from disk |

## Interfaces

| Interface | Used By | Purpose |
|---|---|---|
| `Schedulable` | `Doctor` | Add, book, release, and check slots |
| `Prescribable` | `Doctor` | Write prescription after appointment |

## Exceptions

| Exception | Type | When It Happens |
|---|---|---|
| `SlotUnavailableException` | Checked | Slot is already booked or does not exist |
| `PatientNotFoundException` | Checked | Patient ID is unknown |
| `DoctorNotFoundException` | Checked | Doctor ID is unknown |
| `InvalidOperationException` | Unchecked | Duplicate IDs, double cancel, completing cancelled appointment |

## Flow

```text
Create HospitalManagementSystem
Create patients
Create doctors
Add available slots to doctors
Add patients and doctors to system
Book appointment using patient ID, doctor ID, and time slot
Doctor slot is removed after booking
Complete appointment
Doctor writes prescription
Cancel appointment if needed
Cancelled slot is released back to doctor
Save all data to files
Load all data from files
```

## File Storage

When the demo runs, data is saved into:

```text
data
```

Files:

```text
patients.txt
doctors.txt
appointments.txt
prescriptions.txt
```

## How To Run

From:

```text
C:\programming-task-folder\java\OOP_PROJECT
```

Compile:

```powershell
javac *.java
```

Run:

```powershell
java HospitalMain
```
