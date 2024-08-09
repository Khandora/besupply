package kz.besupply.besupply.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.besupply.besupply.entity.*;
import kz.besupply.besupply.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Tag(name = "Student", description = "Endpoints for student operations")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/subjects/{subjectId}")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Get subject by ID")
    public ResponseEntity<Subject> getSubject(
            @Parameter(description = "Subject ID", required = true) @PathVariable Long subjectId) {
        Optional<Subject> subject = studentService.getSubject(subjectId);
        return subject.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/topics/{topicId}")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Get topic by ID")
    public ResponseEntity<Topic> getTopic(
            @Parameter(description = "Topic ID", required = true) @PathVariable Long topicId) {
        Optional<Topic> topic = studentService.getTopic(topicId);
        return topic.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lessons/{lessonId}")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Get lesson by ID")
    public ResponseEntity<Lesson> getLesson(
            @Parameter(description = "Lesson ID", required = true) @PathVariable Long lessonId) {
        Optional<Lesson> lesson = studentService.getLesson(lessonId);
        return lesson.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/assignments")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Submit an assignment")
    public ResponseEntity<Assignment> submitAssignment(
            @Parameter(description = "Lesson ID", required = true) @RequestParam Long lessonId,
            @Parameter(description = "Text of the assignment", required = true) @RequestParam String text) {
        Assignment assignment = studentService.submitAssignment(lessonId, text);
        return ResponseEntity.ok(assignment);
    }

    @PostMapping("/tests/{testId}")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Submit a test")
    public ResponseEntity<TestResult> submitTest(
            @Parameter(description = "Student ID", required = true) @RequestParam Long studentId,
            @Parameter(description = "Test ID", required = true) @PathVariable Long testId,
            @Parameter(description = "Map of question IDs to answers", required = true) @RequestBody Map<Long, String> answers) {
        TestResult testResult = studentService.submitTest(studentId, testId, answers);
        return ResponseEntity.ok(testResult);
    }

    @GetMapping("/tests/{testId}/results")
    @PreAuthorize("hasRole('STUDENT')")
    @Operation(summary = "Get test result")
    public ResponseEntity<TestResult> getTestResult(
            @Parameter(description = "Student ID", required = true) @RequestParam Long studentId,
            @Parameter(description = "Test ID", required = true) @PathVariable Long testId) {
        Optional<TestResult> testResult = studentService.getTestResult(studentId, testId);
        return testResult.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
