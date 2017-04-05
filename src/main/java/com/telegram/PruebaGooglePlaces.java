package com.telegram;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;
import org.telegram.telegrambots.logging.BotsFileHandler;

import com.google.gson.Gson;
import com.mysql.cj.api.x.Result;
import com.telegram.entities.GooglePlaces;
import com.telegram.updateshandlers.RepitemeHandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;




public class PruebaGooglePlaces {
    private static final String LOGTAG = "MAIN";

    public static void main(String[] args) throws Exception
    {

        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&types=food&name=cruise&key=" + BotConfig.GOOGLE_API_KEY;
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
		
		for(GooglePlaces.Result res : json.getResults()){
			System.out.println(res.toString() + "\n");
		}
    }
    
}