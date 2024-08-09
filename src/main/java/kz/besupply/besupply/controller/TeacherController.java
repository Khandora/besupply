package kz.besupply.besupply.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.besupply.besupply.entity.*;
import kz.besupply.besupply.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
@PreAuthorize("hasRole('TEACHER')")
@Tag(name = "Teacher", description = "Endpoints for teacher operations")
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping("/subjects")
    @Operation(summary = "Create a new subject")
    public ResponseEntity<Subject> createSubject(
            @Parameter(description = "Name of the subject", required = true) @RequestParam String name,
            @Parameter(description = "Description of the subject", required = true) @RequestParam String description) {
        Subject subject = teacherService.createSubject(name, description);
        return ResponseEntity.ok(subject);
    }

    @PostMapping("/topics")
    @Operation(summary = "Create a new topic")
    public ResponseEntity<Topic> createTopic(
            @Parameter(description = "Subject ID", required = true) @RequestParam Long subjectId,
            @Parameter(description = "Name of the topic", required = true) @RequestParam String name,
            @Parameter(description = "Description of the topic", required = true) @RequestParam String description) {
        Topic topic = teacherService.createTopic(subjectId, name, description);
        return ResponseEntity.ok(topic);
    }

    @PostMapping("/lessons")
    @Operation(summary = "Create a new lesson")
    public ResponseEntity<Lesson> createLesson(
            @Parameter(description = "Topic ID", required = true) @RequestParam Long topicId,
            @Parameter(description = "Name of the lesson", required = true) @RequestParam String name,
            @Parameter(description = "Text of the lesson", required = true) @RequestParam String text) {
        Lesson lesson = teacherService.createLesson(topicId, name, text);
        return ResponseEntity.ok(lesson);
    }

    @PostMapping("/assignments")
    @Operation(summary = "Create a new assignment")
    public ResponseEntity<Assignment> createAssignment(
            @Parameter(description = "Lesson ID", required = true) @RequestParam Long lessonId,
            @Parameter(description = "Text of the assignment", required = true) @RequestParam String text) {
        Assignment assignment = teacherService.createAssignment(lessonId, text);
        return ResponseEntity.ok(assignment);
    }

    @PostMapping("/tests")
    @Operation(summary = "Create a new test")
    public ResponseEntity<Test> createTest(
            @Parameter(description = "Topic ID", required = true) @RequestParam Long topicId,
            @Parameter(description = "List of questions", required = true) @RequestBody List<TestQuestion> questions) {
        Test test = teacherService.createTest(topicId, questions);
        return ResponseEntity.ok(test);
    }

    @PostMapping("/subjects/{subjectId}/students/{studentId}")
    @Operation(summary = "Attach a student to a subject")
    public ResponseEntity<Void> attachStudentToSubject(
            @Parameter(description = "Student ID", required = true) @PathVariable Long studentId,
            @Parameter(description = "Subject ID", required = true) @PathVariable Long subjectId) {
        teacherService.attachStudentToSubject(studentId, subjectId);
        return ResponseEntity.ok().build();
    }
}
