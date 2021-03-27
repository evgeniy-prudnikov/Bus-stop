import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ArrayList<Service> service = new ArrayList<>();

        ArrayList<String> stringArray = ReadFileLineByLine.readFile(args[0]);

        for (String str: stringArray) {
            service.add(new Service(str));
        }

        for (Service bus: service) {

            if (bus.completeTime() < 60 * 60 * 1000) { //Any service longer than an hour shall not be included.
                for (Service value : service) {
                    if ((bus.getDepartureTime() == value.getDepartureTime()) && (bus.getArrivalTime() > value.getArrivalTime())) {
                        bus.setEfficient(false);
                        break;
                    } else if ((bus.getDepartureTime() < value.getDepartureTime()) && (bus.getArrivalTime() == value.getArrivalTime())) {
                        bus.setEfficient(false);
                        break;
                    } else if ((bus.getDepartureTime() < value.getDepartureTime()) && (bus.getArrivalTime() > value.getArrivalTime())) {
                        bus.setEfficient(false);
                        break;
                    } else if ((bus.getDepartureTime() == value.getDepartureTime()) && (bus.getArrivalTime() == value.getArrivalTime())) {
                        if (bus.getCompany().equals("Grotty") && value.getCompany().equals("Posh")) {
                            bus.setEfficient(false);
                            break;
                        }
                    }
                }
            } else {
                bus.setEfficient(false);
            }
        }

        service.sort(new Comparator<Service>() {
            @Override
            public int compare(Service service, Service t1) {
                return (int)service.getDepartureTime() - (int)t1.getDepartureTime();
            }
        });

        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        for (Service bus: service) {
            if (bus.isEfficient()) {
                if (bus.getCompany().equals("Posh")) {
                    String str = bus.getCompany() + " " + Service.showTime(bus.getDepartureTime()) + " " +
                            Service.showTime(bus.getArrivalTime()) + "\n";
                    fileOutputStream.write(str.getBytes());

                }
            }
        }

        fileOutputStream.write(new byte[]{13});

        for (Service bus: service) {
            if (bus.isEfficient()) {
                if (bus.getCompany().equals("Grotty")) {
                    String str = bus.getCompany() + " " + Service.showTime(bus.getDepartureTime()) + " " +
                            Service.showTime(bus.getArrivalTime()) + "\n";
                    fileOutputStream.write(str.getBytes());

                }
            }
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}





