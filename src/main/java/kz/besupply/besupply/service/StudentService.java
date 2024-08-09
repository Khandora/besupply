package kz.besupply.besupply.service;

import kz.besupply.besupply.entity.*;

import java.util.Map;
import java.util.Optional;

public interface StudentService {

    Optional<Subject> getSubject(Long subjectId);

    Optional<Topic> getTopic(Long topicId);

    Optional<Lesson> getLesson(Long lessonId);

    Assignment submitAssignment(Long lessonId, String text);

    TestResult submitTest(Long studentId, Long testId, Map<Long, String> answers);

    Optional<TestResult> getTestResult(Long studentId, Long testId);

}
