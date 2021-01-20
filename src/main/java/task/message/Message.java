package task.message;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode
public class Message implements Serializable {
    private LocalDateTime time;
    private String content;
}
