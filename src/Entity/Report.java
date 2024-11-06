package Entity;

import java.util.Date;

/**
 *
 * @author Abraham Hoo Weng Lek
 */

public class Report {
    private Tutor tutor;
    private String action;
    private Date dateTime;
    
    public Report() {
        
    }

    public Report(Tutor tutor, String action, Date dateTime) {
        this.tutor = tutor;
        this.action = action;
        this.dateTime = dateTime;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-15s%-25s", 
                            tutor, action, dateTime);
    }
}
