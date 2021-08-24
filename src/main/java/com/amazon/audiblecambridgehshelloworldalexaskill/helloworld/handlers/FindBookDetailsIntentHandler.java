package com.amazon.audiblecambridgehshelloworldalexaskill.helloworld.handlers;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import database.AlexaSessionDynamoDBHandler;
import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;

public class FindBookDetailsIntentHandler implements RequestHandler{
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("findbookdetails"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        log(input, "Request Loading...");
        logSlots(input)
        ; // create an empty Map
        Map<String,String> exampleMap = new HashMap<>();

        // add some entries to the map
        exampleMap.put("it", "Stephen King"); // add mapping from "key1" to "value1"
        exampleMap.put("hunger games", "Suzanne Collins");// add mapping from "key2" to "value2"
        exampleMap.put("suzanne collins", "Hunger Games");
        exampleMap.put("stephen king", "IT");
        exampleMap.put("the odessy", "Homer");
        exampleMap.put("homer", "The Odessy");
        exampleMap.put("coraline","Neil Gaiman");
        exampleMap.put("neil gaiman", "Coraline");


        Map<String, Slot> slots = getSlots(input);
        String speechTextBookName = "The author of %s is %s";
        String speechTextAuthor = "I found a book called %s by %s";
        String speechText;
        String bookName;
        String author;

        // if we're given a city name
        if(slots.containsKey("BOOK_NAME") && null != slots.get("BOOK_NAME").getValue()) {
            bookName = slots.get("BOOK_NAME").getValue().toLowerCase();
            System.out.println(bookName);
            System.out.println(exampleMap);
            author = exampleMap.get(bookName);
            if(author == null ){speechText = "Author not found";}
            else {
            speechText = String.format(speechTextBookName,bookName, author);}

        } else {
            author = slots.get("AUTHOR").getValue().toLowerCase();
            System.out.println(author);
            System.out.println(exampleMap);
            bookName = exampleMap.get(author);
            if(bookName == null ){speechText = "Book not found";}
            else {
                speechText = String.format(speechTextAuthor,bookName, author);}
        }
        AlexaSessionDynamoDBHandler.saveSessionAttributes(input, bookName, author );
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Book Details", speechText)
                .withReprompt(speechText)
                .build();
    }

    Map<String, Slot> getSlots(HandlerInput input) {
        // this chunk of code gets the slots
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        return Collections.unmodifiableMap(intent.getSlots());
    }

    /**
     * Log slots for easier debugging
     * @param input Input passed to handle
     */
    void logSlots(HandlerInput input) {
        Map<String, Slot> slots = getSlots(input);
        // log slot values including request id and time for debugging
        for(String key : slots.keySet()) {
            log(input, String.format("Slot value key=%s, value = %s", key, slots.get(key).toString()));
        }
    }

    /**
     * Logs debug messages in an easier to search way
     * You can also use system.out, but it'll be harder to work with
     */
    void log(HandlerInput input, String message) {
        System.out.printf("[%s] [%s] : %s]\n",
                input.getRequestEnvelope().getRequest().getRequestId().toString(),
                new Date(),
                message);
    }
    public static void main(String[] args) {

        // create an empty Map
        Map<String,String> exampleMap = new HashMap<>();

        // add some entries to the map
        exampleMap.put("IT", "Stephen King"); // add mapping from "key1" to "value1"
        exampleMap.put("Hunger Games", "Suzanne Collins"); // add mapping from "key2" to "value2"

        // lookup the values using the keys
        System.out.println( exampleMap.get("IT")); // prints "lookup key1: value1"
        System.out.println(exampleMap.get("Hunger Games")); // prints "lookup key2: value2"
    }
}








