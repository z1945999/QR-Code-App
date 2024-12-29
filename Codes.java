package edu.niu.android.qrcodereader;

public class Codes {
    private int id;//Variable for the id number of each entry
    private String format;//String variable for the format type of the code
    private String content;//String variable for the content of the code

    public Codes(int newId, String NewFormat, String NewContent)
    {
        setID(newId);
        setFormat(NewFormat);
        setContent(NewContent);
    }//Default constructor: Set the data from the code scanner.

    //Setters
    private void setFormat(String newFormat) { format = newFormat; }
    private void setID(int newId) { id = newId; }
    private void setContent(String NewContent) { content = NewContent; }

    //Getters
    public int getId() { return id;}
    public String getContent() { return content; }
    public String getFormat() { return format; }
}
