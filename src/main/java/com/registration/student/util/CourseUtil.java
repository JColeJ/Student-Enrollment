package com.registration.student.util;

import java.time.LocalDate;
import java.time.Period;
import com.registration.student.domain.Course;

public class CourseUtil {

    public static String getLastActivityMonth(LocalDate lastActivityDate) {
        return "%tb".formatted(lastActivityDate);
    }

    public static double getPercentComplete(Course course, int lastLecture) {
        return lastLecture * 100.0 / course.lectureCount();
    }

    public static int getMonthsSinceActive(LocalDate lastActivityDate) {

        LocalDate now = LocalDate.now();
        var months = Period.between(lastActivityDate, now).toTotalMonths();
        return (int) months;
    }

    public static void watchLecture(int lectureNumber, LocalDate currentDate, int lastLecture, LocalDate lastActivityDate, String engagementType) {

        lastLecture = Math.max(lectureNumber, lastLecture);
        lastActivityDate = currentDate;
        engagementType = "Lecture " + lastLecture;
    }
}