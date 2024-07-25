package pl.pingwit.basic_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
//    List<StatusEntity> findAllB
}
