package com.registration.student;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.registration.student.domain.Course;
import com.registration.student.domain.Student;
import com.registration.student.enums.CourseCode;
import com.registration.student.enums.StudentFilterType;
import com.registration.student.util.StudentUtil;

@SpringBootApplication
public class StudentApplication {

	private static final int STUDENT_COUNT = 10;

	private static final long MAX_RESULTS = 100;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);

		List<Student> students = Stream.generate(() -> StudentUtil.getRandomStudent())
				.limit(MAX_RESULTS)
				.collect(Collectors.toList());

		System.out.println("Male students: " + StudentUtil.getNumberOfMaleStudents(students));
		System.out.println("Female students: " + StudentUtil.getNumberOfFemaleStudents(students));
		printFilteredStudents("\n", StudentFilterType.ALL_STUDENTS.getFilter());

		List.of(getCourses())
				.forEach(course -> printFilteredStudents("\nStudents enrolled in %s: ".formatted(course.title()),
						(Student student) -> student.engagementMap().containsKey(course)));

		printFilteredStudents("\nStudents with programming experience",
				StudentFilterType.HAS_PROGRAMMING_EXPERIENCE.getFilter());

		printFilteredStudents(
				"\nStudents enrolled in Programming-related courses:",
				(Student student) -> student.engagementMap().keySet().stream()
						.anyMatch(course -> course.title().contains("Programming")));

		printFilteredStudents("\nStudents with no programming experience",
				StudentFilterType.NO_PROGRAMMING_EXPERIENCE.getFilter());

		List.of(StudentFilterType.GRADE_ABOVE__40, StudentFilterType.GRADE_ABOVE__50,
				StudentFilterType.GRADE_ABOVE__60, StudentFilterType.GRADE_ABOVE__70, StudentFilterType.GRADE_ABOVE__80,
				StudentFilterType.GRADE_ABOVE__90)
				.forEach(filterType -> printFilteredStudents(
						"\nStudents with at least one course grade over %d: ".formatted(filterType.getThreshold()),
						filterType.getFilter()));

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
