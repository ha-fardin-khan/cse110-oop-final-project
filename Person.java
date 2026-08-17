abstract class Person{
    private String id;
    private String name;
    private int age;
    private String phone;

    public Person(String id, String name, int age, String phone){
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    public String getPhone(){
        return phone;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public abstract void showInfo();
}
