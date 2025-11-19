// package projectCode;

import java.io.Serializable;
import java.util.ArrayList;

public class DeliverAgent extends person implements Serializable {
    private static final long serialVersionUID = 1L;
    String agentId;
    ArrayList<courier> couriers = new ArrayList<>();
    String agentLocation;

    DeliverAgent(String name, String email, String DOB, String phoneNo, String agentId,String agentLocation) {
        super(name, email, DOB, phoneNo);
        this.agentId = agentId;
        this.agentLocation=agentLocation;
    }

    String allcourier() {
        String courierDetail = "";
        for (int i = 0; i < couriers.size(); i++) {
            courierDetail += couriers.get(i).courierDetail();
        }
        if (courierDetail.equals("")) return "No Couriers Available";
        else return courierDetail;
    }

    String Deliveredcourier() {
        String Delivered = "";
        for (int i = 0; i < couriers.size(); i++) {
            if ("Delivered".equalsIgnoreCase(couriers.get(i).courierStatus)) {
                Delivered += couriers.get(i).courierDetail();
            }
        }
        if (Delivered.equals("")) return "No Couriers Available";
        else return Delivered;
    }

    void updatecourierStatus(String courierId) {
        for (int i = 0; i < couriers.size(); i++) {
            if (couriers.get(i).courierId.equalsIgnoreCase(courierId)) {
                couriers.get(i).courierStatus = "Delivered";
                break;
            }
        }
    }

    String Waitingcourier() {
        String waiting = "";
        for (int i = 0; i < couriers.size(); i++) {
            if (couriers.get(i).courierStatus.equalsIgnoreCase("Waiting")) {
                waiting += couriers.get(i).courierDetail();
            }
        }
        if (waiting.equals("")) return "No Couriers Available";
        else return waiting;
    }
    String displayAgent(){
        return "Delivery Agent Id:"+agentId+"\n"+displayPersonDetail()+"\nCouriers to handle: "+couriers.size();
    }
}
