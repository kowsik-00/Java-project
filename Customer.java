// package projectCode;

import java.util.ArrayList;
import java.io.Serializable;

public class Customer extends person implements Serializable {
    private static final long serialVersionUID = 1L;
    String customerId;
    Address address;
    ArrayList<courier> couriers = new ArrayList<>();

    Customer(String name, String email, String DOB, String phoneNo, Address address, String cusId) {
        super(name, email, DOB, phoneNo);
        customerId = cusId;
        this.address = address;
    }

    void changeAddress(Address a) {
        this.address = a;
    }

    void addcourier(courier courier) {
        couriers.add(courier);
    }

    String courierDetails() {
        String details = "";
        for (int i = 0; i < couriers.size(); i++) {
            details += "To : " + couriers.get(i).name + " - " + couriers.get(i).courierDetail + "\n";
        }
        if (details.equals("")) {
            return "No Couriers Available";
        } else {
            return details;
        }

    }

    String courierTracking() {
        String track = "";
        for (int i = 0; i < couriers.size(); i++) {
            track += "From: " + couriers.get(i).customerDetail.name + " - To: " + couriers.get(i).name + " is "
                    + couriers.get(i).courierStatus + "\n";
        }
        if (track.equals("")) {
            return "No Couriers Available";
        } else {
            return track;
        }
    }

}
