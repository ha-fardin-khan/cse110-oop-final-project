class Prescription{
    private String prescriptionId;
    private Appointment appointment;
    private String medicine;
    private String advice;

    public Prescription(String prescriptionId, Appointment appointment, String medicine, String advice){
        this.prescriptionId = prescriptionId;
        this.appointment = appointment;
        this.medicine = medicine;
        this.advice = advice;
    }

    public String getPrescriptionId(){
        return prescriptionId;
    }

    public Appointment getAppointment(){
        return appointment;
    }

    public String getMedicine(){
        return medicine;
    }

    public String getAdvice(){
        return advice;
    }

    public void showPrescription(){
        System.out.println("Prescription ID : " + prescriptionId);
        System.out.println("Appointment ID : " + appointment.getAppointmentId());
        System.out.println("Patient : " + appointment.getPatient().getName());
        System.out.println("Doctor : " + appointment.getDoctor().getName());
        System.out.println("Medicine : " + medicine);
        System.out.println("Advice : " + advice);
    }

    public String toFileString(){
        return prescriptionId + "|" + appointment.getAppointmentId() + "|" + medicine + "|" + advice;
    }
}
