package com.telegram.updateshandlers;

import com.telegram.BotConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputLocationMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultArticle;
import java.util.ArrayList;
import java.util.List;


public class RepitemeHandlers extends TelegramLongPollingBot {

    private static final String LOGTAG = "REPITEME: ";
    private static final Integer CACHETIME = 86400;
    private static final String THUMBNAILBLUE = "https://lh5.ggpht.com/-kSFHGvQkFivERzyCNgKPIECtIOELfPNWAQdXqQ7uqv2xztxqll4bVibI0oHJYAuAas=w300";

	@Override
    public String getBotToken() {
        return BotConfig.REPITEME_TOKEN;
    }

    public void onUpdateReceived(Update update) {
        try {
        	if (update.hasInlineQuery()) {
                BotLogger.info(LOGTAG, "handling inlinequery");
                /*
                 * This base code can be used to extend it when interested in handling inline queries
                 * not fully working yet
                 * just call:
                 * handleIncomingInlineQuery(update.getInlineQuery());
                 * and modify the called method
                */
                handleIncomingInlineQuery(update.getInlineQuery());
            } else if (update.hasMessage()) {
                BotLogger.info(LOGTAG, "handling message");
                Message message = update.getMessage();
                if (message.hasText()) {
                    // create a object that contains the information to send back the message
                	// The bot will always repeat what the user has said
                    SendMessage sendMessageRequest = new SendMessage();
                    sendMessageRequest.setChatId(message.getChatId().toString()); //who should get the message? the sender from which we got the message...
                    sendMessageRequest.setText("you said: " + message.getText());
                    try {
                    	  BotLogger.info(LOGTAG, sendMessageRequest.toString());
                          sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                    } catch (TelegramApiException e) {
                    //do some error handling of your choice ;)
                    }
                }
                if (update.getMessage().getText().startsWith("I do not know what to say")) {
                	// A little help for undecided users (we take the opportunity to add an inline keyboard
                	SendMessage sm = new SendMessage();
                    sm.setText("I give you two options: ");
                    sm.setChatId(update.getMessage().getChatId());
                    InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> keyboard = new ArrayList<List<InlineKeyboardButton>>();
                    List<InlineKeyboardButton> row = new ArrayList<InlineKeyboardButton>();
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText("I am cool");
                    button.setCallbackData("COOL");
                    InlineKeyboardButton button2 = new InlineKeyboardButton();
                    button2.setText("I am great");
                    button2.setCallbackData("GREAT");
                    row.add(button);
                    row.add(button2);
                    keyboard.add(row);
                    markup.setKeyboard(keyboard);
                    sm.setReplyMarkup(markup);
                    try {
                        sendMessage(sm);
                        BotLogger.info(LOGTAG, sm.toString());
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                } 
            } else if (update.hasCallbackQuery()) {
            	// We want to get back the option selected by the user and print it on the screen
                EditMessageText editMessage = new EditMessageText();
                editMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
                editMessage.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                editMessage.setText("Well done, you finally said: " + update.getCallbackQuery().getData());
                try {
              	    BotLogger.info(LOGTAG, editMessage.toString());
                    editMessageText(editMessage);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    public String getBotUsername() {
        return BotConfig.REPITEME_USER;
    }

    private void handleIncomingInlineQuery(InlineQuery inlineQuery) {
        String query = inlineQuery.getQuery();
        BotLogger.debug(LOGTAG, "Searching: " + query);
        try {
            if (!query.isEmpty()) {
                //List<RaeService.RaeResult> results = raeService.getResults(query);
                //answerInlineQuery(converteResultsToResponse(inlineQuery, results));
                AnswerInlineQuery ianswerInlineQuery = new AnswerInlineQuery();
                ianswerInlineQuery.setInlineQueryId(inlineQuery.getId());
                ianswerInlineQuery.setCacheTime(CACHETIME);
                List<InlineQueryResult> lst = new ArrayList<InlineQueryResult>();;
                
                InlineQueryResultArticle article = new InlineQueryResultArticle();
                InputTextMessageContent messageContent = new InputTextMessageContent();
                messageContent.disableWebPagePreview();
                messageContent.enableMarkdown(true);
                messageContent.setMessageText("I always say hi");
                article.setInputMessageContent(messageContent);
                article.setId(Integer.toString(1));
                article.setTitle("Hi");
                article.setDescription("Description");
                //article.setThumbUrl(THUMBNAILBLUE);
                lst.add(article);
                ianswerInlineQuery.setResults(lst);
                answerInlineQuery(ianswerInlineQuery);
            } else {
                // answerInlineQuery(converteResultsToResponse(inlineQuery, new ArrayList<>()));
                AnswerInlineQuery ianswerInlineQuery = new AnswerInlineQuery();
                ianswerInlineQuery.setInlineQueryId(inlineQuery.getId());
                ianswerInlineQuery.setCacheTime(CACHETIME);
                List<InlineQueryResult> lst = new ArrayList<InlineQueryResult>();;
                
                InlineQueryResultArticle article = new InlineQueryResultArticle();
                InputTextMessageContent messageContent = new InputTextMessageContent();
                messageContent.disableWebPagePreview();
                messageContent.enableMarkdown(true);
                messageContent.setMessageText(new String("Siempre digo hola"));
                article.setInputMessageContent(messageContent);
                article.setId("1");
                article.setThumbUrl(THUMBNAILBLUE);
                article.setTitle("Hola");
                article.setDescription("Descripcion");
                //article.setThumbUrl(THUMBNAILBLUE);
                lst.add(article);
                InputLocationMessageContent messageContent2 = new InputLocationMessageContent();
                messageContent2.setLatitude(new Float(1.1));
                messageContent2.setLongitude(new Float(2.2));
                article.setInputMessageContent(messageContent2);
                lst.add(article);
                ianswerInlineQuery.setResults(lst);
                BotLogger.info(LOGTAG, ianswerInlineQuery.toString());
                answerInlineQuery(ianswerInlineQuery);

            }
        } catch (TelegramApiException e) {
            BotLogger.error(LOGTAG, e);
        }
    }

    
}
