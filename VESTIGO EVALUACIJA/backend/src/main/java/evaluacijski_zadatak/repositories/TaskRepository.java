package evaluacijski_zadatak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import evaluacijski_zadatak.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
