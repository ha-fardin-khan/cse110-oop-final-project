class Appointment{
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String timeSlot;
    private String status;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String timeSlot){
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.timeSlot = timeSlot;
        status = "BOOKED";
    }

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String timeSlot, String status){
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    public String getAppointmentId(){
        return appointmentId;
    }

    public Patient getPatient(){
        return patient;
    }

    public Doctor getDoctor(){
        return doctor;
    }

    public String getTimeSlot(){
        return timeSlot;
    }

    public String getStatus(){
        return status;
    }

    public void cancel(){
        if(status.equals("CANCELLED")){
            throw new InvalidOperationException("Appointment is already cancelled");
        }
        else if(status.equals("COMPLETED")){
            throw new InvalidOperationException("Completed appointment cannot be cancelled");
        }
        else{
            status = "CANCELLED";
        }
    }

    public void complete(){
        if(status.equals("CANCELLED")){
            throw new InvalidOperationException("Cancelled appointment cannot be completed");
        }
        else if(status.equals("COMPLETED")){
            throw new InvalidOperationException("Appointment is already completed");
        }
        else{
            status = "COMPLETED";
        }
    }

    public void showAppointment(){
        System.out.println("Appointment ID : " + appointmentId);
        System.out.println("Patient : " + patient.getName());
        System.out.println("Doctor : " + doctor.getName());
        System.out.println("Time Slot : " + timeSlot);
        System.out.println("Status : " + status);
    }

    public String toFileString(){
        return appointmentId + "|" + patient.getId() + "|" + doctor.getId() + "|" + timeSlot + "|" + status;
    }
}
