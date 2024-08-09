package kz.besupply.besupply.repository;

import kz.besupply.besupply.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {}
