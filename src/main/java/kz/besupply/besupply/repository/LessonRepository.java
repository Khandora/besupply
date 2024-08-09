package kz.besupply.besupply.repository;

import kz.besupply.besupply.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {}
