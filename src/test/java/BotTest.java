import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BotTest {


    @Test
    public void whenBotProcessTextThenSaverIsCalled() { // when..then || given..return
        // arrange - задать данные
        TextSaver saver = new TextSaver();
        Bot bot = new Bot("", "", saver, new AbsSenderMock());
        // act - выполнить действие
        bot.processNonCommandUpdate(createTestUpdate());
        // assert - сравнить результат
        assertTrue(saver.isCalled());
    }

    private Update createTestUpdate() {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(1234L);
        message.setChat(chat);
        update.setMessage(message);
        return update;
    }


    private class AbsSenderMock extends TelegramLongPollingCommandBot {
        @Override
        public String getBotUsername() {
            return null;
        }

        @Override
        public void processNonCommandUpdate(Update update) {
        }

        @Override
        public String getBotToken() {
            return null;
        }

        @Override
        public <T extends Serializable, Method extends BotApiMethod<T>> T execute(Method method) throws TelegramApiException {
            return null;
        }
    }
}
