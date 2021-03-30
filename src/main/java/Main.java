import java.io.FileOutputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ArrayList<Service> service = new ArrayList<>();

        ArrayList<String> stringArray = ReadFileLineByLine.readFile(args[0]);

        for (String str : stringArray) {
            Service ser = new Service(str);
            if (!service.contains(ser))
            service.add(ser);

            if (service.get(service.size() - 1).getArrivalTime() < 60 * 60 * 1000) {
                service.add(new Service(str));
                service.get(service.size() - 1).setDepartureTime(service.get(service.size() - 1).getDepartureTime() + 60 * 60 * 24 * 1000);
                service.get(service.size() - 1).setArrivalTime(service.get(service.size() - 1).getArrivalTime() + 60 * 60 * 24 * 1000);
                service.get(service.size() - 1).setEfficient(false);
            }
        }
        for (Service bus : service) {

            if (bus.completeTime() <= 60 * 60 * 1000) { //Any service longer than an hour shall not be included.
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

        service.sort(Comparator.comparingInt(service2 -> (int) service2.getDepartureTime()));

        try (FileOutputStream fileOutputStream = new FileOutputStream("output.txt")){

        for (Service bus : service) {
            if (bus.isEfficient()) {
                if (bus.getCompany().equals("Posh")) {
                    String str = bus.getCompany() + " " + Service.showTime(bus.getDepartureTime()) + " " +
                            Service.showTime(bus.getArrivalTime()) + "\n";
                    fileOutputStream.write(str.getBytes());

                }
            }
        }

        fileOutputStream.write(new byte[]{13});

        for (Service bus : service) {
            if (bus.isEfficient()) {
                if (bus.getCompany().equals("Grotty")) {
                    String str = bus.getCompany() + " " + Service.showTime(bus.getDepartureTime()) + " " +
                            Service.showTime(bus.getArrivalTime()) + "\n";
                    fileOutputStream.write(str.getBytes());

                }
            }
        }
        }

    }
}


