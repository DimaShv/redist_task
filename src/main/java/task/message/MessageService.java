package task.message;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public interface MessageService {
    void save(MessageCreateCommand command);
    Set<Message> getAllBetweenDates(LocalDateTime from, LocalDateTime to);
    Optional<Message> getLast();
    Set<Message> getAll();
}
