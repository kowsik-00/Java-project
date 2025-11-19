// package projectCode;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 1;
    String doorNumber;
    String streetName;
    String city;
    String state;
    String pincode;

    Address(String doorNumber, String streetName, String city, String state, String pincode) {
        this.doorNumber = doorNumber;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    String formatTheAddress() {
        return doorNumber + "\n" + streetName + "\n" + city + " " + pincode + "\n" + state + ".";
    }
}
