package kz.besupply.besupply.repository;

import kz.besupply.besupply.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {

    Optional<TestResult> findByUserIdAndTestId(Long studentId, Long testId);

}
