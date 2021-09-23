package practica.practica1pwa.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private Integer id;
    private Integer idCollege;
    private String name;
    private String lastName;
    private String phone;
    private String career;

    public Student() {
    }

    public Student(Integer idCollege, String name, String lastName, String phone, String career) {
        this.idCollege = idCollege;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.career = career;
    }
}
