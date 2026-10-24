package com.sms;

public class ReportService {

    public void generateCourseReport(CourseService courseService) {
        System.out.println("===== COURSE REPORT =====");
        courseService.displayCourses();
        System.out.println("===== END OF REPORT =====");
    }
}