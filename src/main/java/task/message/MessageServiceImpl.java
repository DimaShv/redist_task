package task.message;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.config.DateTimeProvider;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final DateTimeProvider dateTimeProvider;
    private final MessageRepository messageRepository;

    @Override
    public void save(MessageCreateCommand command) {
        Message message = new Message();
        message.setTime(dateTimeProvider.getCurrentDateTime());
        message.setContent(command.getContent());
        messageRepository.save(message);
    }

    @Override
    public Set<Message> getAllBetweenDates(LocalDateTime from, LocalDateTime to) {
        return messageRepository.getAllBetweenDateTime(from, to);
    }

    @Override
    public Optional<Message> getLast() {
        return messageRepository.getLast();
    }

    @Override
    public Set<Message> getAll() {
        return messageRepository.getAll();
    }
}
