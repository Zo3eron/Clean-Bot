package com.company.Clean.Bot;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CleanBot extends TelegramLongPollingBot {

    private final UserRepository repository;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        SendMessage returnMessage = new SendMessage();
        Message message = update.getMessage();
        User user = new User();
        Optional<User> optional = this.repository.findById(message.getChatId());
        if (message.getText().equals("/start")) {
            returnMessage.setChatId(message.getChatId());
            returnMessage.setText("Pasdagi bot orqali zakaz berish uchun pasdagi \n Нажмите здесь, чтобы разместить заказ через клик-бот \n⬇️⬇️⬇️⬇️ ");
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("Botga o'ting / Перейти к боту ");
            button.setUrl("https://t.me/Bekobod_Toshkent_taxi_bot");
            rowInline.add(button);
            rowsInline.add(rowInline);
            markupInline.setKeyboard(rowsInline);

            returnMessage.setReplyMarkup(markupInline);
            execute(returnMessage);
        }

        if (message.getText().contains("канча") || message.getText().contains("неч") || message.getText().contains("керак") || message.getText().contains("соат") || message.getText().contains("бек") || message.getText().contains("бекобад") || message.getText().contains("бекабад") || message.getText().contains("сколько ") || message.getText().contains("nech") || message.getText().contains("toshga") || message.getText().contains("bekga")
                || message.getText().contains("bekobodga") || message.getText().contains("pul") || message.getText().contains("qancha") || message.getText().contains("kechga") || message.getText().contains("ertalab")
                || message.getText().contains("soat") || message.getText().contains("ketishi") || message.getText().contains("bosh")
                || message.getText().contains("dastafka") || message.getText().contains("posilka") || message.getText().contains("oldi") || message.getText().contains("кетмокчиман")
                || message.getText().contains("пасилка") || message.getText().contains("бераман") || message.getText().contains("Pustoy moshin kerak") || message.getText().contains("ketishim kerak") || message.getText().contains("Yol kira qancha") || message.getText().contains("Бекабодга") || message.getText().contains("такси кераг") || message.getText().contains("1 киши яккасаройдан олди бош булсин") || message.getText().contains("такси кераг") || message.getText().contains("кетишимиз керак") || message.getText().contains("taksi kerak") || message.getText().contains("такси керак") || message.getText().contains("taksi kk") || message.getText().contains("такси кк") || message.getText().contains("qaytishim kerak") || message.getText().contains("катйтишим керак") || message.getText().contains("почта бор") || message.getText().contains("2 та мошин керак, граница ойбек") || message.getText().contains("тахи керак") || message.getText().contains("тахи кк") || message.getText().contains("taxi kerak") || message.getText().contains("taxi kk") || message.getText().contains("Kk") || message.getText().contains("kk") || message.getText().matches(".*taksi kerak.*") || message.getText().matches(".*ketmoqchiman.*") || message.getText().matches(".*кетмокчиман.*")) {
            if (optional.isEmpty()) {
                user.setChatId(update.getMessage().getChatId());
                user.setUsername(update.getMessage().getChat().getUserName());
                user.setFirstName(message.getChat().getFirstName());
                repository.save(user);
            }
            returnMessage.setChatId(String.valueOf(message.getChatId()));
            returnMessage.setText("Xurmatli \n" +
                    "Klient \n" +
                    "Sizning zakasingiz shafyorlar guruhiga tushdi\n" +
                    "\n" +
                    "Lichkangizda Ishonchlik shafyorlarimiz kutmoqda\n" +
                    "\n" +
                    "Qulaylik uchun bot orqali zakas bering\uD83D\uDC47");
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText("Bot orqali zakaz qilish");
            button.setUrl("https://t.me/Bekobod_Toshkent_taxi_bot");
            rowInline.add(button);
            rowsInline.add(rowInline);
            markupInline.setKeyboard(rowsInline);

            returnMessage.setReplyMarkup(markupInline);

            execute(returnMessage);
            deleteMessage(String.valueOf(message.getChatId()), message.getMessageId());

            //vip grup id bolad
            returnMessage.setChatId("-1002121307892");
            returnMessage.setParseMode(ParseMode.HTML);
            returnMessage.setText("<b><a href='tg://user?id=" + message.getFrom().getId() + "'>" + message.getFrom().getFirstName() + "</a>dan zakaz keldi: </b>\n\n" + message.getText());

            InlineKeyboardMarkup markupInline2 = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline2 = new ArrayList<>();
            List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
            InlineKeyboardButton button2 = new InlineKeyboardButton();
            button2.setText("ZAKAZCHI");
            button2.setUrl("tg://user?id=" + message.getFrom().getId());
            rowInline2.add(button2);
            rowsInline2.add(rowInline2);
            markupInline2.setKeyboard(rowsInline2);
            returnMessage.setReplyMarkup(markupInline2);
            execute(returnMessage);
        }
    }

    public void deleteMessage(String chatId, int messageId) throws TelegramApiException {
        DeleteMessage deleteMessage = new DeleteMessage(chatId, messageId);
        execute(deleteMessage);
    }

    @Override
    public String getBotUsername() {
        return "@clean_universal_bot";
    }

    @Override
    public String getBotToken() {
        return "7019383564:AAFmyPKLNqvCjRS3C8sctcmHArnTYl-KH7s";
    }
}

