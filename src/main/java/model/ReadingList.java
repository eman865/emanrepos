package model;

import java.util.ArrayList;

public class ReadingList {
    private String readingListName;
    private ArrayList<Book> bookTitles;

    public String getReadingListName() {
        return readingListName;
    }

    public void setReadingListName(String readingListName) {
        this.readingListName = readingListName;
    }

    public ArrayList<Book> getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(ArrayList<Book> bookTitles) {
        this.bookTitles = bookTitles;
    }
}
