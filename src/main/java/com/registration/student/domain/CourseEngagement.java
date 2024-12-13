package com.registration.student.domain;

import java.time.LocalDate;
import com.registration.student.util.CourseUtil;

public record CourseEngagement(
        Course course,
        LocalDate enrollmentDate,
        String engagementType,
        int lastLecture,
        LocalDate lastActivityDate,
        int grade
) {
    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType, int grade) {
        this(course, enrollmentDate, engagementType, 0, enrollmentDate, grade);
    }

    public String getCourseCode() {
        return course.courseCode();
    }

    public int getEnrollmentYear() {
        return enrollmentDate.getYear();
    }

    public int getLastActivityYear() {
        return lastActivityDate.getYear();
    }

    @Override
    public String toString() {
        return "%s: %s %d %s [%d]".formatted(
                course.courseCode(),
                CourseUtil.getLastActivityMonth(lastActivityDate),
                getLastActivityYear(),
                engagementType,
                CourseUtil.getMonthsSinceActive(lastActivityDate)
        );
    }
}
