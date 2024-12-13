package com.registration.student.enums;

import java.util.function.Predicate;
import com.registration.student.domain.Student;
import com.registration.student.util.StudentUtil;


public enum StudentFilterType {
    ALL_STUDENTS((Student student) -> false),
    HAS_PROGRAMMING_EXPERIENCE((Student student) -> student.programmingExperience()),
    NO_PROGRAMMING_EXPERIENCE((Student student) -> !student.programmingExperience()),
    GRADE_ABOVE__40(StudentUtil.getStudentGradeGreaterThanFilter(40)),
    GRADE_ABOVE__50(StudentUtil.getStudentGradeGreaterThanFilter(50)),
    GRADE_ABOVE__60(StudentUtil.getStudentGradeGreaterThanFilter(60)),
    GRADE_ABOVE__70(StudentUtil.getStudentGradeGreaterThanFilter(70)),
    GRADE_ABOVE__80(StudentUtil.getStudentGradeGreaterThanFilter(80)),
    GRADE_ABOVE__90(StudentUtil.getStudentGradeGreaterThanFilter(90));

    private Predicate<Student> filter;

    StudentFilterType(Predicate<Student> filter) {
        this.filter = filter;
    }

    public Predicate<Student> getFilter() {
        return filter;
    }

    public int getThreshold() {
        return this.toString().contains("__") ? Integer.valueOf(this.toString().split("__")[1]) : -1;
    }
}