import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class FileStorage{
    public void saveAll(HospitalManagementSystem system, String folderPath) throws IOException{
        File folder = new File(folderPath);

        if(!folder.exists()){
            folder.mkdirs();
        }

        savePatients(system, folderPath + File.separator + "patients.txt");
        saveDoctors(system, folderPath + File.separator + "doctors.txt");
        saveAppointments(system, folderPath + File.separator + "appointments.txt");
        savePrescriptions(system, folderPath + File.separator + "prescriptions.txt");
    }

    private void savePatients(HospitalManagementSystem system, String filePath) throws IOException{
        PrintWriter writer = new PrintWriter(new FileWriter(filePath));

        for(int i = 0; i < system.getPatients().size(); i++){
            writer.println(system.getPatients().get(i).toFileString());
        }

        writer.close();
    }

    private void saveDoctors(HospitalManagementSystem system, String filePath) throws IOException{
        PrintWriter writer = new PrintWriter(new FileWriter(filePath));

        for(int i = 0; i < system.getDoctors().size(); i++){
            writer.println(system.getDoctors().get(i).toFileString());
        }

        writer.close();
    }

    private void saveAppointments(HospitalManagementSystem system, String filePath) throws IOException{
        PrintWriter writer = new PrintWriter(new FileWriter(filePath));

        for(int i = 0; i < system.getAppointments().size(); i++){
            writer.println(system.getAppointments().get(i).toFileString());
        }

        writer.close();
    }

    private void savePrescriptions(HospitalManagementSystem system, String filePath) throws IOException{
        PrintWriter writer = new PrintWriter(new FileWriter(filePath));

        for(int i = 0; i < system.getPrescriptions().size(); i++){
            writer.println(system.getPrescriptions().get(i).toFileString());
        }

        writer.close();
    }

    public HospitalManagementSystem loadAll(String folderPath) throws IOException{
        HospitalManagementSystem system = new HospitalManagementSystem();

        loadPatients(system, folderPath + File.separator + "patients.txt");
        loadDoctors(system, folderPath + File.separator + "doctors.txt");
        loadAppointments(system, folderPath + File.separator + "appointments.txt");
        loadPrescriptions(system, folderPath + File.separator + "prescriptions.txt");

        return system;
    }

    private void loadPatients(HospitalManagementSystem system, String filePath) throws IOException{
        File file = new File(filePath);

        if(!file.exists()){
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null){
            String[] parts = line.split("\\|");

            if(parts.length >= 5){
                Patient patient = new Patient(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4]);
                system.addPatient(patient);
            }

            line = reader.readLine();
        }

        reader.close();
    }

    private void loadDoctors(HospitalManagementSystem system, String filePath) throws IOException{
        File file = new File(filePath);

        if(!file.exists()){
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null){
            String[] parts = line.split("\\|");

            if(parts.length >= 5){
                Doctor doctor = new Doctor(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4]);

                if(parts.length >= 6 && parts[5].length() > 0){
                    String[] slots = parts[5].split(",");

                    for(int i = 0; i < slots.length; i++){
                        doctor.addSlot(slots[i]);
                    }
                }

                system.addDoctor(doctor);
            }

            line = reader.readLine();
        }

        reader.close();
    }

    private void loadAppointments(HospitalManagementSystem system, String filePath) throws IOException{
        File file = new File(filePath);

        if(!file.exists()){
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null){
            String[] parts = line.split("\\|");

            if(parts.length >= 5){
                try{
                    Patient patient = system.findPatient(parts[1]);
                    Doctor doctor = system.findDoctor(parts[2]);
                    Appointment appointment = new Appointment(parts[0], patient, doctor, parts[3], parts[4]);
                    system.addLoadedAppointment(appointment);
                }
                catch(PatientNotFoundException e){
                    System.out.println(e.getMessage());
                }
                catch(DoctorNotFoundException e){
                    System.out.println(e.getMessage());
                }
            }

            line = reader.readLine();
        }

        reader.close();
    }

    private void loadPrescriptions(HospitalManagementSystem system, String filePath) throws IOException{
        File file = new File(filePath);

        if(!file.exists()){
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();

        while(line != null){
            String[] parts = line.split("\\|");

            if(parts.length >= 4){
                try{
                    Appointment appointment = system.findAppointment(parts[1]);
                    Prescription prescription = new Prescription(parts[0], appointment, parts[2], parts[3]);
                    system.addLoadedPrescription(prescription);
                }
                catch(InvalidOperationException e){
                    System.out.println(e.getMessage());
                }
            }

            line = reader.readLine();
        }

        reader.close();
    }
}
