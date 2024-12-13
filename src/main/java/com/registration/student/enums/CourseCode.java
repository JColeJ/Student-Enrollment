package com.registration.student.enums;

import com.registration.student.domain.Course;

public enum CourseCode {
    
    PYMC(new Course("PYMC", "Python Masterclass")),
    JMC(new Course("JMC", "Java Masterclass"));

    private Course course;

    CourseCode(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}