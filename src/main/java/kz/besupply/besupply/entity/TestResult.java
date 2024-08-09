package kz.besupply.besupply.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Table(name = "test_results")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    @ElementCollection
    @CollectionTable(name = "test_answers", joinColumns = @JoinColumn(name = "result_id"))
    @MapKeyJoinColumn(name = "question_id")
    @Column(name = "answer")
    private Map<TestQuestion, String> answers;
    @Column(nullable = false)
    private int correctAnswers;
}
