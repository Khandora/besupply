package kz.besupply.besupply.repository;

import kz.besupply.besupply.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {}
