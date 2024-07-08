package pl.chudziudgi.core.feature.question;

import pl.chudziudgi.core.database.user.User;
import pl.chudziudgi.core.util.ChatUtil;

import java.util.List;
import java.util.Random;

public class QuestionManager {

    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Question getRandomQuestion(List<Question> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    public void toggle(User user) {
        user.chatQuestionStatus = !user.chatQuestionStatus;
        ChatUtil.success(user.getPlayer(), "Zmieniono status wiadomości o pytaniach na: " + ChatUtil.booleanString(user.chatQuestionStatus));
        user.getPlayer().closeInventory();
    }
}
