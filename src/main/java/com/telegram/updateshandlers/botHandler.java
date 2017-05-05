package com.telegram.updateshandlers;

import com.google.gson.Gson;
import com.telegram.BotConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputLocationMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.api.objects.Location;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import com.telegram.entities.GooglePlaces;
import com.telegram.entities.UserBot;
import com.telegram.entities.GooglePlaces.Result;

import org.telegram.telegrambots.exceptions.TelegramApiValidationException;

public class botHandler extends TelegramLongPollingBot {

	private static final String LOGTAG = "botLIS: ";
    private static final Integer CACHETIME = 86400;
    private static final String THUMBNAILBLUE = "https://lh5.ggpht.com/-kSFHGvQkFivERzyCNgKPIECtIOELfPNWAQdXqQ7uqv2xztxqll4bVibI0oHJYAuAas=w300";
    private static HashMap<Integer,UserBot> statusUsers = new HashMap<Integer,UserBot>();
    private static String inputs_user="";
	
	
	@Override
    public String getBotToken() {
        return BotConfig.botLIS_TOKEN;
    }

    public void onUpdateReceived(Update update) {
        try {
        	
        	if (update.hasMessage()) 
        	{
                BotLogger.info(LOGTAG, "handling message");
                Message message = update.getMessage();
               
                System.out.println(message.toString());
                
                
                // create a object that contains the information to send back the message
            	// The bot will always repeat what the user has said
                SendMessage sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString()); //who should get the message? the sender from which we got the message...
               
                
                if(!statusUsers.containsKey(message.getFrom().getId()))
                {
                	System.out.println("AÑADIMOS nuevos usuaario");
                	UserBot new_user=new UserBot(message.getFrom());
                	statusUsers.put(new_user.getU().getId(),new_user);
                }
                
               
                UserBot actual_user =statusUsers.get(message.getFrom().getId());
                System.out.println("USUARIO ACtual"+actual_user.toString());

                //actual_user.setLastReceivecMessage(message.getMessageId());
                
                //Significa que ha pulsado el boton de salir
              
                System.out.println("ESTADO  "+actual_user.getState());
               
                switch(actual_user.getState())
                {
		               
              
	                case 0:
	                	//No esta esperando a ningun mensaje, es un usuario totalmente nuevo
	                	    System.out.println("CASO 0");
	                		String description=" to the LIA-BOT, to start we need to know where are you located, "+
	                            	"so press the bottom to allow us to know your location";
	                    	
	                    	sendMessageRequest.setText("Welcome " +update.getMessage().getFrom().getFirstName() +" "+update.getMessage().getFrom().getLastName() +"  "+
	                    	description);
	                    	sendMessageRequest=MessageLocationButtom(sendMessageRequest);
	                    	System.out.println("Estado antes de actualizar "+actual_user.getState());
	                    	actual_user.setState(1);
	                    	//statusUsers.put(actual_user.getU().getId(),actual_user);
	                    	
	                    	System.out.println("Estado despues de actualizar "+statusUsers.get(message.getFrom().getId()).getState() );

	                    	
	                	
	                	
	                break;
	               
	                case 1:
	                	System.out.println("CASO 1");
	                	//System.out.println(message.getText().equals("PULSA PARA SALIR"));
	                	/*Nos envia la localizacion porque ha presionado enviar localizacion
	                	 * Actulizamos la locaclizacion del usuario y le enviamos las tres opciones*/
	                	if(message.getLocation()!=null)
	                	{
	                		actual_user.setLoc(message.getLocation());
	                    	sendMessageRequest=messageListPlaces(actual_user,sendMessageRequest);
	                    	actual_user.setState(2);
	                    	System.out.println("LOCATION USER "+actual_user.getLoc());

	                	}
	                	System.out.println("mensaje del text"+message.getText());
	                	
	                	if(message.getText()!=null)
	                	{
	                	 if(message.getText().equals("PULSA PARA SALIR"))
		                  	{
		                		//System.out.println("aquiiiii");
		                  		
		                  		sendMessageRequest.setText("GOOD BYE!!! WE HOPE SEE YOU SOON");
		                  		statusUsers.remove(message.getFrom().getId());
		                  		
		                  	}
	                	}
	                	
	                	
	                	
	                		
	                		
	                	
	                break;
	                
	                case 2:
	                	
	                	
	                break;
	                default:
	                	sendMessageRequest.setText("ESTADO NO COMPRESIBLE");
	                	
	                	break;
	                	
                }	
                	
                    try {
                    	if(sendMessageRequest.getText()==null)
                    		sendMessageRequest.setText("I DO NOT UNDERSTAND!!!");
                        sendMessage(sendMessageRequest);
                        BotLogger.info(LOGTAG, sendMessageRequest.toString());
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                } 
            }

         catch (Exception e) {
            BotLogger.error(LOGTAG, e);
        }
    }
    
    public SendMessage MessageLocationButtom(SendMessage message) 
    {
        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        // Create a keyboard row
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton b1=new KeyboardButton();
        KeyboardButton b2=new KeyboardButton();

        b1.setRequestLocation(true);
        b1.setText("PULSA PARA ENVIAR LA LOCALIZACION");
        
        b2.setText("PULSA PARA SALIR");
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row1.add(b1);
        row2.add(b2);
        
        keyboard.add(row1);
        keyboard.add(row2);
        // Create another keyboard row
       
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        message.setReplyMarkup(keyboardMarkup);

        return message;
    }

    public String getBotUsername() 
    {
        return BotConfig.botLIS_USER;
    }
    
    private SendMessage messageListPlaces(UserBot user,SendMessage message)
    {
    	 message.setText("Choose one place of the following list");
    	 InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
    	 ArrayList<List<InlineKeyboardButton>> keyboard = new ArrayList<List<InlineKeyboardButton>>();
    	 ArrayList<InlineKeyboardButton> row=null;
    	 
         //List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        
         ArrayList<GooglePlaces> places=null;
         HashSet<Result> results=new  HashSet<Result>();
         try {
			 places=pruebaGooglePlaces(user.getLoc());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         for(int i=0;i<places.size();i++)
         {
        	 GooglePlaces json=places.get(i);
        	 
        	 for(Result res : json.getResults())
        	 {
			  System.out.println("res.getName()"+res.getName() + "\n");
			   
			  if(!results.contains(res))
			  {
				    results.add(res);
				    InlineKeyboardButton  button=new InlineKeyboardButton();
				    row=new ArrayList<InlineKeyboardButton>();
				    
			        button.setText(res.getName());
					button.setCallbackData(res.getName());
					row.add(button);
					keyboard.add(row);
					
			  }
			    
		     }
         }
         
         markup.setKeyboard(keyboard);
         message.setReplyMarkup(markup);
        // sm.setReplyMarkup(getKeyboard());
         return message;
       
    }
  public static ArrayList<GooglePlaces> pruebaGooglePlaces(Location loc) throws Exception{
    	
    	ArrayList<String> keywords=new ArrayList<String>();
    	keywords.add("lugarintereshistorico");
    	keywords.add("statue");
    	keywords.add("squares");
    	keywords.add("iglesia");
    	keywords.add("museo");
    	keywords.add("teatro");
    	keywords.add("mosque");
    	keywords.add("univeristy");
          
    	Iterator<String> itr = keywords.iterator();
    	HashMap<String,Gson> sitios=new HashMap<String,Gson>();
    	ArrayList<GooglePlaces> result=new ArrayList<GooglePlaces>();
        while (itr.hasNext()) 
        {
          
    
		    	String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+loc.getLatitude()+","+loc.getLongitude()+"&"+
		    	"radius=200&keyword="+itr.next()+
		    	"&key=" + BotConfig.GOOGLE_API_KEY;
		    	HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
				con.setRequestMethod("GET");
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
		
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				Gson gson = new Gson();
				
				GooglePlaces json = gson.fromJson(response.toString(), GooglePlaces.class);
				result.add(json);
				/*for(Result res : json.getResults()){
					System.out.println(res.toString() + "\n");
				}*/
				
				
        }
		/*Gson gson = new Gson();
		GooglePlaces json = gson.fromJson(response.toString(), GooglePlaces.class);
		
		for(Result res : json.getResults()){
			System.out.println(res.toString() + "\n");
		}*/
        return result;
    }

   

    
}
