import java.io.IOException;

class HospitalMain{
    public static void main(String[] args) {
        HospitalManagementSystem system = new HospitalManagementSystem();

        Patient patient1 = new Patient("P101", "Rahim", 22, "01711111111", "Fever");
        Patient patient2 = new Patient("P102", "Karim", 30, "01822222222", "Headache");

        Doctor doctor1 = new Doctor("D101", "Dr. Ayesha", 40, "01933333333", "Medicine");
        Doctor doctor2 = new Doctor("D102", "Dr. Hasan", 45, "01644444444", "Cardiology");

        doctor1.addSlot("10:00 AM");
        doctor1.addSlot("11:00 AM");
        doctor2.addSlot("02:00 PM");

        try{
            system.addPatient(patient1);
            system.addPatient(patient2);
            system.addDoctor(doctor1);
            system.addDoctor(doctor2);

            Appointment appointment1 = system.bookAppointment("P101", "D101", "10:00 AM");
            system.completeAppointment(appointment1.getAppointmentId(), "Napa 500mg", "Take rest and drink water");

            Appointment appointment2 = system.bookAppointment("P102", "D102", "02:00 PM");
            system.cancelAppointment(appointment2.getAppointmentId());

            System.out.println("Patients");
            System.out.println("--------");
            system.showAllPatients();

            System.out.println("Doctors");
            System.out.println("-------");
            system.showAllDoctors();

            System.out.println("Appointments");
            System.out.println("------------");
            system.showAllAppointments();

            System.out.println("Prescriptions");
            System.out.println("-------------");
            system.showAllPrescriptions();

            FileStorage storage = new FileStorage();
            storage.saveAll(system, "data");

            HospitalManagementSystem loadedSystem = storage.loadAll("data");

            System.out.println("Loaded Data From File");
            System.out.println("---------------------");
            System.out.println("Patients loaded : " + loadedSystem.getPatients().size());
            System.out.println("Doctors loaded : " + loadedSystem.getDoctors().size());
            System.out.println("Appointments loaded : " + loadedSystem.getAppointments().size());
            System.out.println("Prescriptions loaded : " + loadedSystem.getPrescriptions().size());
        }
        catch(PatientNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(DoctorNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch(SlotUnavailableException e){
            System.out.println(e.getMessage());
        }
        catch(InvalidOperationException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println("File error: " + e.getMessage());
        }
    }
}
