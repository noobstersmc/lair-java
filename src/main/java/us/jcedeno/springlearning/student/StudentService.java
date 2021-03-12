package us.jcedeno.springlearning.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Api method that lists all users in the set
     * 
     * @return Json array of students or List of students. Might be empty
     */
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    /**
     * Api method that adds a student to the set if not present already.
     * 
     * @param student Student object to be parsed in
     * @throws IllegalStateException if the email is already registered.
     */
    public void addNewStudent(Student student) {
        /** Query a student with that email */
        var studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        /** If the student is present, throw exception */
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        /** Otherwise save them onto database */
        studentRepository.save(student);

    }

    /**
     * Api method that deletes an student if they exists
     * 
     * @param studentId to be queried.
     * @throws IllegalStateException if the provided Id doesn't actually exist.
     */
    public void deleteStudent(Long studentId) {
        /** Throw an exception if the provided Id doesn't exist */
        if (!studentRepository.existsById(studentId))
            throw new IllegalStateException("student with id " + studentId + "does not exist.");
        /** Delete the student */
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + "does not exist."));

        /** Validate the name */
        if (name != null && name.length() > 0 && !student.getName().equals(name))
            student.setName(name);

        /** Validate the email */
        if (email != null && email.length() > 0 && !student.getEmail().equals(email)) {
            /** Just some random checks here */
            if (!email.toLowerCase().endsWith(".edu"))
                throw new IllegalStateException("The provided email address isn't a .edu domain");

            if (!email.toLowerCase().contains("@"))
                throw new IllegalStateException("The email must follow format name@domain.edu");

            /** Now check if the provided email is not already taken */
            if (studentRepository.findStudentByEmail(email).isPresent())
                throw new IllegalStateException("The provided email " + email + " is already taken.");

            /** Update the email */
            student.setEmail(email);
        }

    }

}
