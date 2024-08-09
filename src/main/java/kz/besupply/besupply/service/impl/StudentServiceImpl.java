package kz.besupply.besupply.service.impl;

import kz.besupply.besupply.entity.*;
import kz.besupply.besupply.repository.*;
import kz.besupply.besupply.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;
    private final LessonRepository lessonRepository;
    private final AssignmentRepository assignmentRepository;
    private final TestRepository testRepository;
    private final TestResultRepository testResultRepository;
    private final TestQuestionRepository testQuestionRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<Subject> getSubject(Long subjectId) {
        return subjectRepository.findById(subjectId);
    }

    @Override
    public Optional<Topic> getTopic(Long topicId) {
        return topicRepository.findById(topicId);
    }

    @Override
    public Optional<Lesson> getLesson(Long lessonId) {
        return lessonRepository.findById(lessonId);
    }

    @Override
    public Assignment submitAssignment(Long lessonId, String text) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
        Assignment assignment = Assignment.builder().lesson(lesson).text(text).build();
        return assignmentRepository.save(assignment);
    }

    @Override
    public TestResult submitTest(Long studentId, Long testId, Map<Long, String> answers) {
        User student = userRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Test test = testRepository.findById(testId).orElseThrow(() -> new IllegalArgumentException("Test not found"));

        int correctAnswersCount = (int) test.getQuestions().stream()
                .filter(q -> q.getCorrectAnswer().equals(answers.get(q.getId())))
                .count();

        TestResult testResult = TestResult.builder()
                .user(student)
                .test(test)
                .answers(answers.entrySet().stream().collect(Collectors.toMap(
                        e -> testQuestionRepository.findById(e.getKey()).orElseThrow(() -> new IllegalArgumentException("Question not found")),
                        Map.Entry::getValue
                )))
                .correctAnswers(correctAnswersCount)
                .build();

        return testResultRepository.save(testResult);
    }

    @Override
    public Optional<TestResult> getTestResult(Long studentId, Long testId) {
        return testResultRepository.findByUserIdAndTestId(studentId, testId);
    }
}
