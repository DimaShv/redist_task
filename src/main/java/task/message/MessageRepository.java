package task.message;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface MessageRepository {
    void save(Message message);
    Set<Message> getAll();
    Set<Message> getAllBetweenDateTime(LocalDateTime from, LocalDateTime to);
    Optional<Message> getLast();
}
