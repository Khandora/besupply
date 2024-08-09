package kz.besupply.besupply.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "test_questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    @Column(nullable = false)
    private String question;
    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option")
    private Set<String> options;
    @Column(nullable = false)
    private String correctAnswer;
}
