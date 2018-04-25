package edu.nmsu.cs.lcpdandu;

//Each notification will have properties ID, Title, Description, Date, Time, and AMPM.
//They will be created as objects with their following attributes.
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
