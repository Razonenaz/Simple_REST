
public class Student implements Comparable<Object> {

    private int id;
    private String name = "";
    private String course = "";

    public Student() {

    }

    public Student(String name, String course) {
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public Student setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public int compareTo(Object o) {
        Student that = (Student) o;
        int compareValue;
        if (this.id > that.id) {
            compareValue = 1;
        } else if (this.id < that.id) {
            compareValue = -1;
        } else {
            compareValue = 0;
        }
        return compareValue;
    }
}
