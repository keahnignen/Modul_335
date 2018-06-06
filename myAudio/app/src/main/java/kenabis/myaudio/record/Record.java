package kenabis.myaudio.record;

public class Record
{
    private String title;
    private String date;

    public Record(String title, String date)
    {
        this.title = title;
        this.date = date;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
