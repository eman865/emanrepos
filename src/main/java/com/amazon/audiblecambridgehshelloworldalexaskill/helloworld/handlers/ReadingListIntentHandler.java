package com.amazon.audiblecambridgehshelloworldalexaskill.helloworld.handlers;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import database.AlexaSessionDynamoDBHandler;
import java.util.*;

import static com.amazon.ask.request.Predicates.intentName;

public class ReadingListIntentHandler implements RequestHandler{
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName("sayReadingList"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        log(input, "Request Loading...");

        String speechText = "test";
        String bookName;
        String author;



      //  AlexaSessionDynamoDBHandler.saveSessionAttributes(input, bookName, author );
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Book Details", speechText)
                .withReprompt(speechText)
                .build();
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

}








