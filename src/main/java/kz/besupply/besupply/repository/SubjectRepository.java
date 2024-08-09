package kz.besupply.besupply.repository;

import kz.besupply.besupply.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
