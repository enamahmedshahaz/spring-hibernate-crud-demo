package com.shahaz.hibernatecrud;

import com.shahaz.hibernatecrud.dao.StudentDao;
import com.shahaz.hibernatecrud.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HibernateCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateCrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDao) {
        return runner -> {
            testStudentCRUD(studentDao);
        };
    }
    //please truncate student table in your database
    //each time before running this app to avoid errors.
    private void testStudentCRUD(StudentDao studentDao) {

        //create student object
        System.out.println("Creating student object");
        Student theStudent1 = new Student("Enam", "Ahmed", "shahaz@gmail.com");
        Student theStudent2 = new Student("Lailatul", "Ferdous", "mousumi@gmail.com");
        Student theStudent3 = new Student("Emon", "Ahmed", "sanglap@gmail.com");


        //Saving to database
        System.out.println("Saving student object");
        studentDao.save(theStudent1);
        studentDao.save(theStudent2);
        studentDao.save(theStudent3);


        //show id of saved item
        System.out.println("Student saved. Id of a student: " + theStudent1.getId());
        System.out.println("\n\n");


        //read student from database
        System.out.println("Getting Student with id: " + theStudent1.getId());
        Student myStudent = studentDao.findById(theStudent1.getId());
        System.out.println("Student is: " + myStudent);
        System.out.println("\n\n");


        //read all Students from database
        System.out.println("Reading All Students");
        List<Student> allStudents = studentDao.findAll();
        System.out.println("All Students size: " + allStudents.size());
        for (Student aStudent : allStudents) {
            System.out.println("Student: " + aStudent);
        }
        System.out.println("\n\n");


        //Querying Students from database
        System.out.println("Querying by last Name- Ahmed");
        List<Student> studentList = studentDao.findByLastName("Ahmed");
        System.out.println("Found Student: " + studentList.size());
        for (Student aStudent : studentList) {
            System.out.println("Student: " + aStudent);
        }
        System.out.println("\n\n");

        //Update a Student in database
        int studentId = 3;
        Student studentWithId3 = studentDao.findById(studentId);
        System.out.println("Getting a student with Id 3: " + studentWithId3);
        studentWithId3.setEmail("updated@gmail.com");
        System.out.println("Updating student with new email : updated@gmail.com");
        studentDao.update(studentWithId3);
        System.out.println("After Update: ");
        System.out.println(studentDao.findById(studentId));
        System.out.println("\n\n");

        //delete a Student in database
        int toDeleteStudentId = 3;
        Student toDeleteStudent = studentDao.findById(toDeleteStudentId);
        System.out.println("Getting a student with Id 3: " + toDeleteStudent);
        System.out.println("Deleting student");
        studentDao.delete(toDeleteStudentId);

        System.out.println("After delete: ");
        allStudents = studentDao.findAll();
        System.out.println("All Students size: " + allStudents.size());
        for (Student aStudent : allStudents) {
            System.out.println("Student: " + aStudent);
        }
        System.out.println("\n\n");

        //delete All Student in database
        System.out.println("Deleting All Students");
        int numDeleted = studentDao.deleteAll();
        System.out.println("Deleted Student number is: " + numDeleted);
        allStudents = studentDao.findAll();
        System.out.println("All Students size: " + allStudents.size());
        System.out.println("\n\n");


    }

}
