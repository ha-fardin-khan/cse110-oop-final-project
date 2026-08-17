class Patient extends Person{
    private String problem;

    public Patient(String id, String name, int age, String phone, String problem){
        super(id, name, age, phone);
        this.problem = problem;
    }

    public String getProblem(){
        return problem;
    }

    public void setProblem(String problem){
        this.problem = problem;
    }

    @Override
    public void showInfo(){
        System.out.println("Patient ID : " + getId());
        System.out.println("Name : " + getName());
        System.out.println("Age : " + getAge());
        System.out.println("Phone : " + getPhone());
        System.out.println("Problem : " + problem);
    }

    public String toFileString(){
        return getId() + "|" + getName() + "|" + getAge() + "|" + getPhone() + "|" + problem;
    }
}
