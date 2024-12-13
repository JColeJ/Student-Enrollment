package com.registration.student.util;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import com.registration.student.domain.*;
import com.registration.student.enums.*;

public class StudentUtil {

    public static final String MALE_SYMBOL = "M";
    public static final String FEMALE_SYMBOL = "F";
    public static final String UNSPECIFIED_SYMBOL = "U";

    public static final int MIN_YEAR = 1900;
    public static final int MAX_YEAR = 2000;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 100;

    public static final Random RANDOM = new Random();

    public static double getPercentComplete(Student student, Course course) {
        var info = student.engagementMap().get(course);
        return (info == null) ? 0.0d : CourseUtil.getPercentComplete(course, info.lastLecture());
    }

    public static void watchLecture(Student student, Course course, int lectureNumber, int month, int year) {
        var activity = student.engagementMap().get(course);
        if (activity != null) {
            CourseUtil.watchLecture(lectureNumber, LocalDate.of(year, month, 1), year, null, "");
        }
    }

    private static String getRandomVal(String... data) {
        return data[RANDOM.nextInt(data.length)];
    }

    public static Student getRandomStudent(Course... courses) {
        Student student = new Student(
                getRandomVal(List.of(CountryCode.values()).stream().map(countryCode -> countryCode.getIso2())
                        .toArray(String[]::new)), // Use country code enum
                RANDOM.nextInt(MIN_YEAR, MAX_YEAR),
                RANDOM.nextInt(MIN_AGE, MAX_AGE),
                getRandomVal(
                        List.of(Gender.values()).stream().map(gender -> gender.getSymbol()).toArray(String[]::new)),
                RANDOM.nextBoolean());

        StudentUtil.addCourses(student, courses);
        List.of(courses).forEach(course -> coursesWatched(student, course));
        return student;
    }

    private static void addCourses(Student student, Course[] courses) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCourses'");
    }

    private static void coursesWatched(Student student, Course course) {
        int lecture = RANDOM.nextInt(1, course.lectureCount());
        int year = RANDOM.nextInt(student.yearEnrolled(), MAX_YEAR);
        int month = RANDOM.nextInt(1, 13);
        if (year == (MAX_YEAR - 1)) {
            if (month > LocalDate.now().getMonthValue()) {
                month = LocalDate.now().getMonthValue();
            }
        }
        watchLecture(student, course, lecture, month, year);
    }

    public static long getNumberOfMaleStudents(List<Student> students) {
        return students.stream()
                .filter(s -> s.gender().isMale())
                .count();
    }

    public static long getNumberOfFemaleStudents(List<Student> students) {
        return students.stream()
                .filter(s -> s.gender().isFemale())
                .count();
    }

    public static Predicate<Student> getStudentGradeGreaterThanFilter(int grade) {
        return (Student student) -> student.engagementMap().values().stream()
                .filter(courseEngagement -> courseEngagement.grade() > grade).count() > 0;
    }
}