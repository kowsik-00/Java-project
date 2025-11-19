// package projectCode;

import java.io.Serializable;
import java.util.ArrayList;

public class Admin extends person implements Serializable{
    private static final long serialVersionUID = 1L;
    String AdminId;
    ArrayList<DeliverAgent> listOfAgent=new ArrayList<>();

    Admin(String name, String email, String DOB, String phoneNo,String id) {
        super(name, email, DOB, phoneNo);
        AdminId=id;
    }
    void addDeliveryAgent(DeliverAgent agent){
        listOfAgent.add(agent);
    }
    void assigncourierToDeliverAgent(courier c,String Id){
        for (int i = 0; i < listOfAgent.size(); i++) {
            if (listOfAgent.get(i).agentId.equalsIgnoreCase(Id)) {
                c.courierStatus="Waiting";
                listOfAgent.get(i).couriers.add(c);
                break;
            }
        }
    }
    String displayThecourier(){
        String details="";
        for (int i = 0; i < listOfAgent.size(); i++) {
            for (int j = 0; j < listOfAgent.get(i).couriers.size(); j++) {
                details+=listOfAgent.get(i).couriers.get(i).courierDetail();
            }
        }
        return details;
    }
    void removeDeliveryAgent(String Id){
        for (int i = 0; i < listOfAgent.size(); i++) {
            if (listOfAgent.get(i).agentId.equalsIgnoreCase(Id)) {
                listOfAgent.remove(i);
            }
        }
    }
    
}
