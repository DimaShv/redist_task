package task.message;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import task.config.DateTimeProvider;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class MessageZRepository implements MessageRepository {
    private static final String REDIS_MESSAGE_Z_KEY = "zmessage";
    private final RedisTemplate<String, Message> redisTemplate;

    @Override
    public void save(Message message) {
        redisTemplate.opsForZSet().add(REDIS_MESSAGE_Z_KEY, message, DateTimeProvider.getMillisFromLocalDateTime(message.getTime()));
    }

    @Override
    public Set<Message> getAll() {
        return redisTemplate.opsForZSet().range(REDIS_MESSAGE_Z_KEY, 0, -1);
    }

    @Override
    public Set<Message> getAllBetweenDateTime(LocalDateTime from, LocalDateTime to) {
        return redisTemplate.opsForZSet()
                .rangeByScore(REDIS_MESSAGE_Z_KEY,
                              DateTimeProvider.getMillisFromLocalDateTime(from),
                              DateTimeProvider.getMillisFromLocalDateTime(to)
                             );
    }

    @Override
    public Optional<Message> getLast() {
        return redisTemplate.opsForZSet().range(REDIS_MESSAGE_Z_KEY, -1, -1).stream().findAny();
    }
}
