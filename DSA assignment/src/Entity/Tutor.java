package Entity;

/**
 *
 * @author Abraham Hoo Weng Lek
 */

public class Tutor {
    private String name;
    private String gender;
    private int age;
    private String course;
    private int yearInBoard;
    
    public Tutor(){
        
    }

    public Tutor(String name, String gender, int age, String course, int yearInBoard) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.course = course;
        this.yearInBoard = yearInBoard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getYearInBoard() {
        return yearInBoard;
    }

    public void setYearInBoard(int yearInBoard) {
        this.yearInBoard = yearInBoard;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-15s%-10s%-25s%-17s", 
                            name, gender, age, course, yearInBoard);
}
    
    
}
