package com.registration.student;

import java.util.List;
import java.util.stream.Stream;
import java.util.function.Predicate;

import com.registration.student.domain.*;
import com.registration.student.enums.*;
import com.registration.student.util.StudentUtil;

public class Main {

    private static final int STUDENT_COUNT = 10;

    

    public static void main(String[] args) {
        printFilteredStudents("\n", StudentFilterType.ALL_STUDENTS.getFilter());

        List.of(getCourses())
        .forEach(course -> printFilteredStudents("\nStudents enrolled in %s: ".formatted(course.title()), (Student student) -> student.engagementMap().containsKey(course)));

        // TODO: restructure the packages to add a JSON filestore for storing student and course data

        printFilteredStudents("\nStudents with programming experience", StudentFilterType.HAS_PROGRAMMING_EXPERIENCE.getFilter());    

        printFilteredStudents(
            "\nStudents enrolled in Programming-related courses:",
            (Student student) -> student.engagementMap().keySet().stream()
                .anyMatch(course -> course.title().contains("Programming"))
            );

       printFilteredStudents("\nStudents with no programming experience", StudentFilterType.NO_PROGRAMMING_EXPERIENCE.getFilter());     
       
       List.of(StudentFilterType.GRADE_ABOVE__40, StudentFilterType.GRADE_ABOVE__50, 
            StudentFilterType.GRADE_ABOVE__60, StudentFilterType.GRADE_ABOVE__70, StudentFilterType.GRADE_ABOVE__80,
            StudentFilterType.GRADE_ABOVE__90)
                    .forEach(filterType -> 
                    printFilteredStudents("\nStudents with at least one course grade over %d: ".formatted(filterType.getThreshold()), filterType.getFilter()));

    }

    private static Stream<Student> getStudentStream() {
         return Stream.generate(() -> StudentUtil.getRandomStudent(getCourses()))
                .limit(STUDENT_COUNT);
    }

    private static void printFilteredStudents(String metricTitle, Predicate<Student> filter) {
        System.out.println(metricTitle);
        getStudentStream().filter(filter).forEach(System.out::println);
    }

    private static Course[] getCourses() {
        return List.of(CourseCode.values()).stream().map(courseCode -> courseCode.getCourse()).toArray(Course[]::new);
    }
}