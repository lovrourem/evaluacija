package evaluacijski_zadatak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import evaluacijski_zadatak.entities.Task;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findAllByUserId(Long userId);
}
