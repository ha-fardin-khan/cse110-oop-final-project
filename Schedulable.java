interface Schedulable{
    void addSlot(String timeSlot);
    void bookSlot(String timeSlot) throws SlotUnavailableException;
    void releaseSlot(String timeSlot);
    boolean hasSlot(String timeSlot);
}
