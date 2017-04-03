package com.telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.logging.BotsFileHandler;

import com.google.gson.Gson;
import com.telegram.entities.GooglePlaces;
import com.telegram.entities.GooglePlaces.Result;
import com.telegram.entities.WikiMedia;
import com.telegram.entities.WikiMedia.Page;
/*import org.telegram.updateshandlers.ChannelHandlers;
import org.telegram.updateshandlers.CommandsHandler;
import org.telegram.updateshandlers.DirectionsHandlers;
import org.telegram.updateshandlers.FilesHandlers;
import org.telegram.updateshandlers.RaeHandlers;
import org.telegram.updateshandlers.TransifexHandlers;
import org.telegram.updateshandlers.WeatherHandlers;
import org.telegram.updateshandlers.WebHookExampleHandlers;*/
import com.telegram.updateshandlers.RepitemeHandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Main class to create all bots
 * @date 20 of June of 2015
 */
public class Main {
    private static final String LOGTAG = "MAIN";

    public static void main(String[] args) throws Exception{
    	
    	pruebaGooglePlaces();
    	//pruebaWikiMedia();
    	System.exit(0);
    	
        BotLogger.setLevel(Level.ALL);
        BotLogger.registerLogger(new ConsoleHandler());
        try {
            BotLogger.registerLogger(new BotsFileHandler());
        } catch (IOException e) {
            BotLogger.severe(LOGTAG, e);
        }
        BotLogger.info(LOGTAG, "intializing...");
        try {
            ApiContextInitializer.init();
            TelegramBotsApi telegramBotsApi = createTelegramBotsApi();
            try {
            	telegramBotsApi.registerBot(new RepitemeHandlers());

            } catch (TelegramApiException e) {
                BotLogger.error(LOGTAG, e);
            }
        } catch (Exception e) {
            BotLogger.error(LOGTAG, e);
        }
    }
    
    public static void pruebaWikiMedia() throws Exception{
    	String url = "https://en.wikipedia.org/w/api.php?action=query&prop=categories&generator=geosearch&ggsradius=500&ggscoord=40.41694|-3.70361";
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
		WikiMedia json = gson.fromJson(response.toString(), WikiMedia.class);
		
		for(Page page : json.getQuery().getPages()){
			System.out.println(page.toString() + "\n");
		}
    }
    public static void pruebaGooglePlaces() throws Exception{
    	
    	String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=point_of_interest&key=" + BotConfig.GOOGLE_API_KEY;
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
		
		for(Result res : json.getResults()){
			System.out.println(res.toString() + "\n");
		}
    }
    
    private static TelegramBotsApi createTelegramBotsApi() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi;
        if (!BuildVars.useWebHook) {
            // Default (long polling only)
            telegramBotsApi = createLongPollingTelegramBotsApi();
        } else if (!BuildVars.pathToCertificatePublicKey.isEmpty()) {
            // Filled a path to a pem file ? looks like you're going for the self signed option then, invoke with store and pem file to supply.
            telegramBotsApi = createSelfSignedTelegramBotsApi();
            //telegramBotsApi.registerBot(new WebHookExampleHandlers());
        } else {
            // Non self signed, make sure you've added private/public and if needed intermediate to your cert-store.
            telegramBotsApi = createNoSelfSignedTelegramBotsApi();
            //telegramBotsApi.registerBot(new WebHookExampleHandlers());
        }
        return telegramBotsApi;
    }

    /**
     * @brief Creates a Telegram Bots Api to use Long Polling (getUpdates) bots.
     * @return TelegramBotsApi to register the bots.
     */
    private static TelegramBotsApi createLongPollingTelegramBotsApi() {
        return new TelegramBotsApi();
    }

    /**
     * @brief Creates a Telegram Bots Api to use Long Polling bots and webhooks bots with self-signed certificates.
     * @return TelegramBotsApi to register the bots.
     *
     * @note https://core.telegram.org/bots/self-signed#java-keystore for generating a keypair in store and exporting the pem.
    *  @note Don't forget to split the pem bundle (begin/end), use only the public key as input!
     */
    private static TelegramBotsApi createSelfSignedTelegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(BuildVars.pathToCertificateStore, BuildVars.certificateStorePassword, BuildVars.EXTERNALWEBHOOKURL, BuildVars.INTERNALWEBHOOKURL, BuildVars.pathToCertificatePublicKey);
    }

    /**
     * @brief Creates a Telegram Bots Api to use Long Polling bots and webhooks bots with no-self-signed certificates.
     * @return TelegramBotsApi to register the bots.
     *
     * @note Coming from a set of pem files here's one way to do it:
     * @code{.sh}
     * openssl pkcs12 -export -in public.pem -inkey private.pem > keypair.p12
     * keytool -importkeystore -srckeystore keypair.p12 -destkeystore server.jks -srcstoretype pkcs12
     * #have (an) intermediate(s) to supply? first:
     * cat public.pem intermediate.pem > set.pem (use set.pem as -in)
     * @endcode
     */
    private static TelegramBotsApi createNoSelfSignedTelegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(BuildVars.pathToCertificateStore, BuildVars.certificateStorePassword, BuildVars.EXTERNALWEBHOOKURL, BuildVars.INTERNALWEBHOOKURL);
    }
}
