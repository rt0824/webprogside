package mk.ukim.finki.wp.jan2022.g1.repository;

import mk.ukim.finki.wp.jan2022.g1.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
