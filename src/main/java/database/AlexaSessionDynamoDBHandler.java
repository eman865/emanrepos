package database;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import model.Book;
import model.ReadingList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlexaSessionDynamoDBHandler {

    public static void saveSessionAttributes(HandlerInput input, String key, String value) {
        try {
            Map<String, Object> persistentAttributes = new HashMap<>(input.getAttributesManager().getPersistentAttributes());
            Book book = new Book();
            book.setBookTitle("Harry Potter");
            book.setBookAuthor("J.K Rowling");

            ArrayList<Book> books = new ArrayList<>();
            books.add(book);

            ReadingList readingList = new ReadingList();
            readingList.setReadingListName("emansReadingList");
            readingList.setBookTitles(books);

            persistentAttributes.put(key, readingList);
            input.getAttributesManager().setPersistentAttributes(persistentAttributes);
            input.getAttributesManager().savePersistentAttributes();
        } catch (Exception ex) {
            System.out.println(ex.toString());
            throw ex;
        }
    }
}
