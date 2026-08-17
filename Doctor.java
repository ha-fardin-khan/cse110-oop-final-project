import java.util.ArrayList;

class Doctor extends Person implements Schedulable, Prescribable{
    private String specialization;
    private ArrayList<String> availableSlots;

    public Doctor(String id, String name, int age, String phone, String specialization){
        super(id, name, age, phone);
        this.specialization = specialization;
        availableSlots = new ArrayList<String>();
    }

    public String getSpecialization(){
        return specialization;
    }

    public void setSpecialization(String specialization){
        this.specialization = specialization;
    }

    public ArrayList<String> getAvailableSlots(){
        return availableSlots;
    }

    @Override
    public void addSlot(String timeSlot){
        if(!availableSlots.contains(timeSlot)){
            availableSlots.add(timeSlot);
        }
    }

    @Override
    public void bookSlot(String timeSlot) throws SlotUnavailableException{
        if(availableSlots.contains(timeSlot)){
            availableSlots.remove(timeSlot);
        }
        else{
            throw new SlotUnavailableException("Slot is not available: " + timeSlot);
        }
    }

    @Override
    public void releaseSlot(String timeSlot){
        if(!availableSlots.contains(timeSlot)){
            availableSlots.add(timeSlot);
        }
    }

    @Override
    public boolean hasSlot(String timeSlot){
        return availableSlots.contains(timeSlot);
    }

    @Override
    public Prescription writePrescription(String prescriptionId, Appointment appointment, String medicine, String advice){
        return new Prescription(prescriptionId, appointment, medicine, advice);
    }

    @Override
    public void showInfo(){
        System.out.println("Doctor ID : " + getId());
        System.out.println("Name : " + getName());
        System.out.println("Age : " + getAge());
        System.out.println("Phone : " + getPhone());
        System.out.println("Specialization : " + specialization);
        System.out.println("Available Slots : " + availableSlots);
    }

    public String toFileString(){
        String slots = "";

        for(int i = 0; i < availableSlots.size(); i++){
            slots = slots + availableSlots.get(i);

            if(i < availableSlots.size() - 1){
                slots = slots + ",";
            }
        }

        return getId() + "|" + getName() + "|" + getAge() + "|" + getPhone() + "|" + specialization + "|" + slots;
    }
}
