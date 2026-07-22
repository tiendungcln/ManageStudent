public class Student {

    private int id;
    private String name;
    private String className;
    private double gpa;

    public Student(int id, String name, String className, double gpa){
        this.id = id;
        this.name = name;
        this.className = className;
        this.gpa = gpa;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getClassName(){
        return className;
    }

    public void setClassName(String className){
        this.className = className;
    }

    public double getGpa(){
        return gpa;
    }

    public void setGpa(double gpa){
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Họ và tên: %-10s | Lớp: %-10s | GPA: %.1f",
                id, name, className, gpa
        );
    }

}
