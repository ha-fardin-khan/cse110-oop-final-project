import java.util.ArrayList;

class HospitalManagementSystem{
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Appointment> appointments;
    private ArrayList<Prescription> prescriptions;

    public HospitalManagementSystem(){
        patients = new ArrayList<Patient>();
        doctors = new ArrayList<Doctor>();
        appointments = new ArrayList<Appointment>();
        prescriptions = new ArrayList<Prescription>();
    }

    public ArrayList<Patient> getPatients(){
        return patients;
    }

    public ArrayList<Doctor> getDoctors(){
        return doctors;
    }

    public ArrayList<Appointment> getAppointments(){
        return appointments;
    }

    public ArrayList<Prescription> getPrescriptions(){
        return prescriptions;
    }

    public void addPatient(Patient patient){
        if(idExists(patient.getId())){
            throw new InvalidOperationException("Duplicate ID found: " + patient.getId());
        }

        patients.add(patient);
    }

    public void addDoctor(Doctor doctor){
        if(idExists(doctor.getId())){
            throw new InvalidOperationException("Duplicate ID found: " + doctor.getId());
        }

        doctors.add(doctor);
    }

    private boolean idExists(String id){
        for(int i = 0; i < patients.size(); i++){
            if(patients.get(i).getId().equals(id)){
                return true;
            }
        }

        for(int i = 0; i < doctors.size(); i++){
            if(doctors.get(i).getId().equals(id)){
                return true;
            }
        }

        return false;
    }

    public Patient findPatient(String patientId) throws PatientNotFoundException{
        for(int i = 0; i < patients.size(); i++){
            if(patients.get(i).getId().equals(patientId)){
                return patients.get(i);
            }
        }

        throw new PatientNotFoundException("Patient not found: " + patientId);
    }

    public Doctor findDoctor(String doctorId) throws DoctorNotFoundException{
        for(int i = 0; i < doctors.size(); i++){
            if(doctors.get(i).getId().equals(doctorId)){
                return doctors.get(i);
            }
        }

        throw new DoctorNotFoundException("Doctor not found: " + doctorId);
    }

    public Appointment findAppointment(String appointmentId){
        for(int i = 0; i < appointments.size(); i++){
            if(appointments.get(i).getAppointmentId().equals(appointmentId)){
                return appointments.get(i);
            }
        }

        throw new InvalidOperationException("Appointment not found: " + appointmentId);
    }

    public Appointment bookAppointment(String patientId, String doctorId, String timeSlot)
            throws PatientNotFoundException, DoctorNotFoundException, SlotUnavailableException{
        Patient patient = findPatient(patientId);
        Doctor doctor = findDoctor(doctorId);

        doctor.bookSlot(timeSlot);

        String appointmentId = "A" + (appointments.size() + 1);
        Appointment appointment = new Appointment(appointmentId, patient, doctor, timeSlot);
        appointments.add(appointment);

        return appointment;
    }

    public void cancelAppointment(String appointmentId){
        Appointment appointment = findAppointment(appointmentId);
        appointment.cancel();
        appointment.getDoctor().releaseSlot(appointment.getTimeSlot());
    }

    public Prescription completeAppointment(String appointmentId, String medicine, String advice){
        Appointment appointment = findAppointment(appointmentId);
        appointment.complete();

        String prescriptionId = "PR" + (prescriptions.size() + 1);
        Prescription prescription = appointment.getDoctor().writePrescription(prescriptionId, appointment, medicine, advice);
        prescriptions.add(prescription);

        return prescription;
    }

    public void showAllPatients(){
        for(int i = 0; i < patients.size(); i++){
            patients.get(i).showInfo();
            System.out.println();
        }
    }

    public void showAllDoctors(){
        for(int i = 0; i < doctors.size(); i++){
            doctors.get(i).showInfo();
            System.out.println();
        }
    }

    public void showAllAppointments(){
        for(int i = 0; i < appointments.size(); i++){
            appointments.get(i).showAppointment();
            System.out.println();
        }
    }

    public void showAllPrescriptions(){
        for(int i = 0; i < prescriptions.size(); i++){
            prescriptions.get(i).showPrescription();
            System.out.println();
        }
    }

    void addLoadedAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    void addLoadedPrescription(Prescription prescription){
        prescriptions.add(prescription);
    }
}
