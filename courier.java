// package projectCode;

import java.io.Serializable;
import java.time.LocalDate;

public class courier implements Serializable {
    private static final long serialVersionUID = 1L;
    String courierId;
    Customer customerDetail;
    Address receiverAddress;
    String name;
    String receiverPhoneNumber;
    double courierWight;
    String courierDetail;
    String courierStatus;
    String Paymethod;
    LocalDate date;

    courier(String courierId, Customer customerDetail,  Address receiverAddress, String name,
            String receiverPhoneNumber, double courierWight, String courierDetail, String Paymethod,LocalDate date) {
        this.courierId = courierId;
        this.customerDetail = customerDetail;
        this.receiverAddress = receiverAddress;
        this.name = name;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.courierWight = courierWight;
        this.courierDetail = courierDetail;
        courierStatus = "Processing";
        this.Paymethod = Paymethod;
        this.date=date;
    }

    double courierAmount() {
        return courierWight  * 20;
    }

    double gst() {
        return ((courierAmount() * 18) / 100);
    }

    double totalbillAmount() {
        return (courierAmount() + gst());
    }

    String courierDetail() {

        String RESET = "\u001B[0m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String PURPLE = "\u001B[35m";
        String CYAN = "\u001B[36m";
        String detail="\nSent By: " + customerDetail.name + "\nReceived By: " + name + "\nAddress: \n"
        + receiverAddress.formatTheAddress() + "\nReceiver Phone Number: " + receiverPhoneNumber
        + "\nCourier weight: " + courierWight + "g\nCourier Detail: " + courierDetail;
        if (("Processing").equalsIgnoreCase(courierStatus)) {
            return detail+RESET+CYAN+ "\nStatus: "+courierStatus+RESET+YELLOW+"\n";
        }else if (("Waiting").equalsIgnoreCase(courierStatus)) {
            return detail+RESET+PURPLE+ "\nStatus: "+courierStatus+RESET+YELLOW+"\n";
        }else if(("Delivered").equalsIgnoreCase(courierStatus)){
            return detail+RESET+GREEN+ "\nStatus: "+courierStatus+RESET+YELLOW+"\n";
        }
        return  detail+RESET+CYAN+ "\nStatus: "+courierStatus+RESET+YELLOW+"\n";     
    }

    String Bill() {
        String bill = "╔═ Bill ════════════════════════════╗\nDate:"+date+"\nFrom \n\t" + customerDetail.name + ",\n\t"
                + customerDetail.PhoneNumber + "\n\t"
                + customerDetail.address.doorNumber + "," + customerDetail.address.streetName + ",\n\t" + customerDetail.address.state + "  "
                + customerDetail.address.pincode + "\n\t" + customerDetail.address.city + ".\n\nTo\n\t" + name + ",\n\t" + receiverPhoneNumber
                + "\n\t" + receiverAddress.doorNumber + "," + receiverAddress.streetName + ",\n\t"
                + receiverAddress.state + "  " + receiverAddress.pincode + "\n\t" + receiverAddress.city
                + "\n\nCourier Details :\n\t" + courierDetail
                + "\n\n╔═══════════════╦════════════════╗\n║  Weight(g)\t║     Amount     ║"
                +   "\n╠═══════════════╬════════════════╣\n║   "
                + courierWight
                + "\t║\t" + courierAmount()
                + "\t ║\n╠═══════════════╩═╦══════════════╣\n║   Gst(18%)  \t  ║\t" + gst()
                + "\t ║\n╚═════════════════╩══════════════╝\n     Total Bill Amount\t: "
                + totalbillAmount() + "\n     Payment Method\t: " + Paymethod
                + "\n \n          --- Thank You ---\n\n╚═══════════════════════════════════╝";

        return bill;
    }
    // String vertical = "║";
    // String horizontal = "═";
    // String topLeft = "╔";
    // String topCross = "╦";
    // String topRight = "╗";
    // String midLeft = "╠";
    // String midCross = "╬";
    // String midRight = "╣";
    // String botLeft = "╚";
    // String botCross = "╩";
    // String botRight = "╝";
}
