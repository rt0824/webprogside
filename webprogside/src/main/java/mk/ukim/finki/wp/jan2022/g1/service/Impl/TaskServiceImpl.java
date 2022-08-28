package mk.ukim.finki.wp.jan2022.g1.service.Impl;

import mk.ukim.finki.wp.jan2022.g1.model.Task;
import mk.ukim.finki.wp.jan2022.g1.model.TaskCategory;
import mk.ukim.finki.wp.jan2022.g1.model.User;
import mk.ukim.finki.wp.jan2022.g1.model.exceptions.InvalidTaskIdException;
import mk.ukim.finki.wp.jan2022.g1.repository.TaskRepository;
import mk.ukim.finki.wp.jan2022.g1.repository.UserRepository;
import mk.ukim.finki.wp.jan2022.g1.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Task> listAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return this.taskRepository.findById(id).orElseThrow(InvalidTaskIdException::new);
    }

    @Override
    public Task create(String title, String description, TaskCategory category,
                       List<Long> assignees, LocalDate dueDate) {
        List<User> assignees1 = this.userRepository.findAllById(assignees);
        Task t = new Task(title,description,category,assignees1,dueDate);
        return this.taskRepository.save(t);
    }

    @Override
    public Task update(Long id, String title, String description, TaskCategory category, List<Long> assignees) {
        Task t = this.findById(id);
        List<User> assignees1 = this.userRepository.findAllById(assignees);
        t.setAssignees(assignees1);
        t.setTitle(title);
        t.setDescription(description);
        t.setCategory(category);

        return this.taskRepository.save(t);
    }

    @Override
    public Task delete(Long id) {
        Task t = this.findById(id);
        this.taskRepository.delete(t);
        return t;
    }

    @Override
    public Task markDone(Long id) {
        return null;
    }

    @Override
    public List<Task> filter(Long assigneeId, Integer lessThanDayBeforeDueDate) {
        return null;
    }
}
