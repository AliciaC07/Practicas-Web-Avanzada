package practica.practica1pwa.services;

import org.springframework.stereotype.Service;
import practica.practica1pwa.models.Student;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    //private static StudentService studentService = null;
    private final List<Student> students = new ArrayList<>();

//    public static StudentService getInstance(){
//        if (studentService == null){
//            studentService = new StudentService();
//        }
//        return studentService;
//    }

    public StudentService() {
        Student student = new Student(20174585, "Hilda", "Franco","809-567-8845", "ADM");
        Student student1 = new Student(20171565, "Carlos", "Perez","809-555-3696", "ITT");
        Student student2 = new Student(20176988, "Sandra", "Lopez","809-632-1214", "IIS");
        Student student3 = new Student(20170465, "Alicia", "Cruz","809-613-3107", "ISC");
        addStudent(student);
        addStudent(student1);
        addStudent(student2);
        addStudent(student3);
    }

    public Student addStudent(Student student){
        if (students.isEmpty()){
            student.setId(1);
        }else {
            student.setId(students.size()+1);
        }
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents(){
        return students;
    }


    public Optional<Student> findById(Integer id){
        for (Student student: students) {
            if (student.getId().equals(id)){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    public Optional<Student> findByIdCollege(Integer idCollege){
        for (Student student: students) {
            if (student.getIdCollege().equals(idCollege)){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    public Student deleteById(Integer id){
        Student student = findById(id).orElseThrow(()-> new EntityNotFoundException("This student could not be found"));
        students.remove(student);
        return student;
    }

    public Student updateStudentById(Student old, Student student){
        old.setCareer(student.getCareer());
        old.setLastName(student.getLastName());
        old.setName(student.getName());
        old.setPhone(student.getPhone());
        return old;

    }


}
