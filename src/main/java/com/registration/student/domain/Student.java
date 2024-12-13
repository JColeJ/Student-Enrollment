package com.registration.student.domain;

import java.util.HashMap;
import java.util.UUID;
import com.registration.student.enums.*;

public record Student(String studentId, String countryCode, int yearEnrolled, int ageEnrolled, Gender gender,
        boolean programmingExperience, HashMap<Course, CourseEngagement> engagementMap) {

    public Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender,
            boolean programmingExperience) {
        this(UUID.randomUUID().toString(), countryCode, yearEnrolled, ageEnrolled, Gender.fromSymbol(gender),
                programmingExperience, new HashMap<>());
    }

    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", countryCode='" + countryCode + '\'' +
                ", yearEnrolled=" + yearEnrolled +
                ", ageEnrolled=" + ageEnrolled +
                ", gender='" + gender.getSymbol() + '\'' +
                ", programmingExperience=" + programmingExperience +
                ", engagementMap=" + engagementMap +
                '}';
    }

}
