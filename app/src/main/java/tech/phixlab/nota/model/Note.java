package tech.phixlab.nota.model;

public class Note {
    private  int id;
    private String title;
    private String body;
    private String dateTime;



    public Note(int id, String title, String body, String dateTime) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.dateTime = dateTime;
    }

    public Note() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
