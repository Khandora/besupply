package kz.besupply.besupply.service.impl;

import kz.besupply.besupply.entity.*;
import kz.besupply.besupply.repository.*;
import kz.besupply.besupply.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;
    private final LessonRepository lessonRepository;
    private final AssignmentRepository assignmentRepository;
    private final TestRepository testRepository;
    private final UserRepository userRepository;

    @Override
    public Subject createSubject(String name, String description) {
        Subject subject = Subject.builder().name(name).description(description).build();
        return subjectRepository.save(subject);
    }

    @Override
    public Topic createTopic(Long subjectId, String name, String description) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("Subject not found"));
        Topic topic = Topic.builder().subject(subject).name(name).description(description).build();
        return topicRepository.save(topic);
    }

    @Override
    public Lesson createLesson(Long topicId, String name, String text) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new IllegalArgumentException("Topic not found"));
        Lesson lesson = Lesson.builder().topic(topic).name(name).text(text).build();
        return lessonRepository.save(lesson);
    }

    @Override
    public Assignment createAssignment(Long lessonId, String text) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new IllegalArgumentException("Lesson not found"));
        Assignment assignment = Assignment.builder().lesson(lesson).text(text).build();
        return assignmentRepository.save(assignment);
    }

    @Override
    public Test createTest(Long topicId, List<TestQuestion> questions) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new IllegalArgumentException("Topic not found"));
        Test test = Test.builder().topic(topic).questions(Set.copyOf(questions)).build();
        return testRepository.save(test);
    }

    @Override
    public void attachStudentToSubject(Long studentId, Long subjectId) {
        User student = userRepository.findById(studentId).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new IllegalArgumentException("Subject not found"));
        subject.getStudents().add(student);
        subjectRepository.save(subject);
    }
}
