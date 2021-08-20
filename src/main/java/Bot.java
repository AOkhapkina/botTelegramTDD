import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public final class Bot extends TelegramLongPollingCommandBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private AbsSender sender;
    private TextSaver saver;


    public Bot(String botName, String botToken, TextSaver saver, AbsSender sender) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;

        this.saver = saver;
        this.sender = sender !=null ? sender : this;

        register(new HelpCommand());
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        saver.saveText(message.getText());
        sendAnswer(message.getChat().getId(), "Ok");
    }

    void sendAnswer(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        message.enableMarkdown(true); // we need markdown
        try {
            this.sender.execute(message);
        } catch (TelegramApiException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

}