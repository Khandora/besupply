package kz.besupply.besupply.service;

import kz.besupply.besupply.entity.*;

import java.util.List;

public interface TeacherService {

    Subject createSubject(String name, String description);

    Topic createTopic(Long subjectId, String name, String description);

    Lesson createLesson(Long topicId, String name, String text);

    Assignment createAssignment(Long lessonId, String text);

    Test createTest(Long topicId, List<TestQuestion> questions);

    void attachStudentToSubject(Long studentId, Long subjectId);

}
