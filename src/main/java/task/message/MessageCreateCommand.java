package task.message;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class MessageCreateCommand {
    @NotNull
    private String content;
}
