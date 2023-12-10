package dev.trifanya.taskmanagementsystem.validator;

import dev.trifanya.taskmanagementsystem.exception.UnavailableActionException;
import dev.trifanya.taskmanagementsystem.model.User;
import dev.trifanya.taskmanagementsystem.model.task.Task;
import org.springframework.stereotype.Component;

@Component
public class CommentValidator {
    public void validateNewComment(Task task, User commentAuthor) {
        if (!task.getAuthor().equals(commentAuthor) && !task.getPerformer().equals(commentAuthor)) {
            throw new UnavailableActionException("Комментарий к задаче может опубликовать только ее автор или исполнитель.");
        }
    }

    public void validateUpdatedComment(User commentAuthor, User commentModifier) {
        if (!commentAuthor.equals(commentModifier)) {
            throw new UnavailableActionException("Комментарий к задаче может редактировать только автор комментария.");
        }
    }
}