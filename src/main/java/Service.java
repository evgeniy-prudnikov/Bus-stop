import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Service {

    private String company;
    private long departureTime;
    private long arrivalTime;

    public boolean isEfficient() {
        return isEfficient;
    }

    private boolean isEfficient = true;
    private final static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");


    public Service(String str) throws ParseException {
        String[] strings = str.split(" ");
        company = strings[0];
        departureTime = formatter.parse(strings[1]).getTime();
        arrivalTime = formatter.parse(strings[2]).getTime();
        if (arrivalTime < departureTime) {
            arrivalTime += (60 * 60 * 24 * 1000); //ms
        }
    }

    public String getCompany() {
        return company;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public long completeTime(){
        return arrivalTime - departureTime;
    }

    public static String showTime(long dateTime) {
        return formatter.format(new Date(dateTime));

    }

    public void setEfficient(boolean efficient) {
        isEfficient = efficient;
    }
}

