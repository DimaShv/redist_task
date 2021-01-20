package task.message;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("publish")
    public ResponseEntity<Void> publish(@RequestBody @Valid MessageCreateCommand command) {
        messageService.save(command);
        return ResponseEntity.ok().build();
    }

    @GetMapping("getLast")
    public ResponseEntity<Message> getLast() {
        return ResponseEntity.of(messageService.getLast());
    }

    @GetMapping("getAll")
    public ResponseEntity<Set<Message>> getAll() {
        return ResponseEntity.ok(messageService.getAll());
    }

    @PostMapping("getByTime")
    public ResponseEntity<Set<Message>> getByTime(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        return ResponseEntity.ok(messageService.getAllBetweenDates(from, to));
    }
}
