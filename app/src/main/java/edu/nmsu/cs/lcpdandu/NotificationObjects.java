package edu.nmsu.cs.lcpdandu;

/**
 * Created by tonib on 3/26/2018.
 */

public class NotificationObjects {

    public String ID;
    public String Title;
    public String Description;
    public String Date;
    public String Time;
    public String AMPM;

    public NotificationObjects(String ID, String Title, String Description, String Date, String Time, String AMPM){
        this.ID = ID;
        this.Title = Title;
        this.Description = Description;
        this.Date = Date;
        this.Time = Time;
        this.AMPM = AMPM;
    }
}
