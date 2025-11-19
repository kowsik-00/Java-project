
// import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CourierManagement {
    ArrayList<courier> courierManagement = new ArrayList<>();
    ArrayList<Customer> customerList = new ArrayList<>();
    ArrayList<DeliverAgent> agents = new ArrayList<>();
    Admin adminmain;

    public static void main(String[] args) {

        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String PURPLE = "\u001B[35m";
        String CYAN = "\u001B[36m";

        Scanner sc = new Scanner(System.in);
        Filehandel file = new Filehandel();
        file.createFile();
        CourierManagement management = new CourierManagement();
        management.customerList = file.getCustomerFile();
        management.courierManagement = file.getcourierFile();
        management.agents = file.getagentFile();
        management.adminmain = file.getAdminFile();
        // LocalDate currentDate = LocalDate.now();
        // System.out.println("Current Date: " + currentDate);
        System.out.println(
                BLUE + "╔═══════════════════════════════════════╗\n" +
                        "║░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░║\n"+
                        "║░▀▀█░█▀▀░░░█▀▀░█▀█░█░█░█▀▄░▀█▀░█▀▀░█▀▄░║\n" +
                        "║░▄▀░░▀▀█░░░█░░░█░█░█░█░█▀▄░░█░░█▀▀░█▀▄░║\n" +
                        "║░▀▀▀░▀▀▀░░░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀▀▀░▀▀▀░▀░▀░║\n" +
                        "║░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░║\n"+
                        "╚═══════════════════════════════════════╝" + RESET);
        // for (int i = 0; i < management.customerList.size(); i++) {
        // System.out.println(management.customerList.get(i).name);
        // }
        // System.out.println(management.customerList.size());

        // System.out.println(management.courierManagement.get(0).Bill());
         
        Outer: while (true) {
            System.out.println(
                    PURPLE + "\n╔════════════════════════════╗\n║            Menu            ║\n╠════════════════════════════╣\n║1.Login                     ║\n║2.Sign Up                   ║\n║3.Change Password           ║\n║4.Exit                      ║\n╚════════════════════════════╝"
                            + RESET);
            int choice = 0;
            while (true) {
                try {

                    System.out.print(YELLOW + "Enter your choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println(RESET + RED + "Invalid chioce menu" + RESET + YELLOW);
                }

            }

            String npass = "";
            String cpass = "";
            String pass = "";
            if (choice == 1) {
                file.getLogindata();
                System.out.print("Enter your e-mail   :");
                String email = sc.nextLine();
                if (email.split("@")[0].equalsIgnoreCase("agent")) {
                    int count = -1;
                    for (int i = 0; i < file.email.size(); i++) {
                        if (file.pass.get(i).equalsIgnoreCase("-") && file.email.get(i).equalsIgnoreCase(email)) {
                            count = i;
                        }
                    }
                    if (count >= 0) {
                        System.out.print("Enter a Password          :");
                        npass = sc.nextLine();
                        passCheck: while (true) {
                            if (npass.length() >= 8) {
                                break passCheck;
                            } else {
                                System.out.println(RESET + RED + "your password is not secure" + RESET);
                            }
                            System.out.print(YELLOW + "Enter a secure Password   :");
                            npass = sc.nextLine();
                        }
                        System.out.print("Enter a Confirm Password  :");
                        cpass = sc.nextLine();
                        boolean condition = cpass.equals(npass);
                        if (condition) {
                            file.modifyDeliveragent(count, cpass);
                        } else {
                            System.out.println(RESET + RED + "E-mail or password not matched." + RESET);
                            break;
                        }

                    } else {
                        System.out.print(YELLOW + "Enter your password :");
                        pass = sc.nextLine();
                    }

                } else {
                    System.out.print("Enter your password :");
                    pass = sc.nextLine();
                }

                String emailfound = RESET + RED + "E-mail or password not matched." + RESET;
                int count = -1;
                for (int i = 0; i < file.email.size(); i++) {
                    if (file.email.get(i).equalsIgnoreCase(email)
                            && (file.pass.get(i).equalsIgnoreCase(pass) || file.pass.get(i).equalsIgnoreCase("-"))) {
                        emailfound = RESET + GREEN + "Login Successful!" + RESET;
                        count = i;
                    }
                }
                if (count >= 0) {

                    System.out.println(emailfound);
                    if (email.split("@")[0].equalsIgnoreCase("admin")) {
                        management.admin(email);
                    } else if (email.split("@")[0].equalsIgnoreCase("agent")) {
                        management.agent(email);
                    } else {
                        management.customer(email);
                    }
                } else {
                    System.out.println(emailfound);
                    continue Outer;
                }
            } else if (choice == 2) {
                file.getLogindata();
                int customerid = management.customerList.size();
                System.out.print(RESET + YELLOW + "Enter a e-mail           :");
                String semail = sc.nextLine();
                int count = -1;
                for (int i = 0; i < file.email.size(); i++) {
                    if (file.email.get(i).equalsIgnoreCase(semail) && file.pass.get(i).equalsIgnoreCase(cpass)) {
                        count = i;
                    }
                }
                // System.out.println(semail.split("@")[1]);
                emailcheck: while (true) {
                    if (semail.split("@")[1].equalsIgnoreCase("gmail.com")
                            || semail.split("@")[1].equalsIgnoreCase("gmail.in")) {
                        break emailcheck;
                    } else {
                        System.out.println(RESET + RED + "You Enter a wrong format of email " + RESET);
                    }
                    System.out.print(RESET + YELLOW + "Enter correct format of e-mail:");
                    semail = sc.nextLine();
                }

                if (count >= 0) {
                    System.out.println(RESET + RED + "You Already have a account." + RESET);
                } else {
                    System.out.print(RESET + YELLOW + "Enter your name          :");
                    String name = sc.nextLine();
                    System.out.print("Enter your Birth Year    :");
                    String DOB = sc.nextLine();
                    int currentyear = 2025;
                    if ((currentyear) > Integer.parseInt(DOB)) {
                        System.out.print("Enter your Phone Number  :");
                        String phoneNo = sc.nextLine();
                        number: while (true) {
                            if (phoneNo.length() == 10) {
                                break number;
                            } else {
                                System.out.print("Enter correct Phone Number :");
                                phoneNo = sc.nextLine();
                            }
                        }
                        String id = "Cus" + (++customerid);

                        System.out.print("Enter your door Number :");
                        String doorNumber = sc.nextLine();
                        System.out.print("Enter your Street Name :");
                        String streetName = sc.nextLine();
                        System.out.print("Enter your city Name   :");
                        String city = sc.nextLine();
                        System.out.print("Enter your State       :");
                        String state = sc.nextLine();
                        System.out.print("Enter your Pincode     :");
                        String pincode = sc.nextLine();
                        pin: while (true) {
                            if (pincode.length() == 6) {
                                break pin;
                            } else {
                                System.out.print("Enter correct Pincode         :");
                                pincode = sc.nextLine();
                            }
                        }
                        Address address = new Address(doorNumber, streetName, city, state, pincode);
                        Customer c = new Customer(name, semail, DOB, phoneNo, address, id);
                        management.customerList.add(c);
                        System.out.print("Enter a New Password      :");
                        npass = sc.nextLine();
                        passCheck: while (true) {
                            if (npass.length() >= 8) {
                                break passCheck;
                            } else {
                                System.out.println(RESET + RED + "your password is not secure" + RESET);
                            }
                            System.out.print(YELLOW + "Enter a secure Password   :");
                            npass = sc.nextLine();
                        }
                        System.out.print("Enter a Confirm Password  :");
                        cpass = sc.nextLine();
                        if (npass.equals(cpass)) {
                            file.addCustomerLog(semail.split("@")[0], semail, cpass);
                            System.out.println(RESET + GREEN + "Your sign-up was successful." + RESET);

                        } else {
                            System.out.println(RESET + RED + "You Enter a wrong password." + RESET);
                        }
                    } else {
                        System.out.println(RESET + RED + "Invalid Birth year" + RESET);
                    }

                }

            } else if (choice == 3) {
                file.getLogindata();
                System.out.print(YELLOW + "Enter your e-mail : ");
                String email = sc.nextLine();
                System.out.print("Enter a password :");
                pass = sc.nextLine();
                int count = -1;
                for (int j = 0; j < file.email.size(); j++) {
                    if (file.email.get(j).equalsIgnoreCase(email) && file.pass.get(j).equalsIgnoreCase(pass)) {
                        count = j;
                    }
                }
                if (count >= 0) {
                    System.out.print("Enter a new Password :");
                    npass = sc.nextLine();
                    passCheck: while (true) {
                        if (npass.length() >= 8) {
                            break passCheck;
                        } else {
                            System.out.println(RESET + RED + "your password is not secure" + RESET);
                        }
                        System.out.print(RESET + YELLOW + "Enter a secure Password   :");
                        npass = sc.nextLine();
                    }
                    System.out.print("Enter a Confirm Password :");
                    cpass = sc.nextLine();
                    if (email.split("@")[0].equalsIgnoreCase("admin")) {
                        file.modifyAdminPass(email, cpass);
                    } else if (email.split("@")[0].equalsIgnoreCase("agent")) {
                        file.modifyAgent(email, cpass);
                    } else {
                        file.modifyCustomerlog(email, cpass);
                    }
                    System.out.println(RESET + GREEN + "password Successfully Changed." + RESET);
                } else {
                    System.out.println(RESET + RED + "E-mail or password not matched." + RESET);
                }

            } else if (choice == 4) {
                file.addInCustomerFile(management.customerList);
                file.addcourierFile(management.courierManagement);
                file.addagentFile(management.agents);
                file.addAdminFile(management.adminmain);
                System.out.println("\n--- Thank you for visiting ---\n");
                break Outer;
            } else {
                System.out.println(RESET + RED + "Please enter a valid option." + RESET);
            }

        }

    }

    void admin(String email) {
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String PURPLE = "\u001B[35m";
        String CYAN = "\u001B[36m";
        Scanner sc = new Scanner(System.in);
        int noId = agents.size();
        Filehandel file = new Filehandel();
        Admin admin = new Admin(null, null, null, null, null);
        int adminid = 1;

        if (adminmain != null) {
            admin = adminmain;
        } else {
            System.out.print(YELLOW + "Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your Birth Year   : ");
            String DOB = sc.nextLine();
            System.out.print("Enter your Phone Number: ");
            String phoneNo = sc.nextLine();
            number: while (true) {
                if (phoneNo.length() == 10) {
                    break number;
                } else {
                    System.out.print("Enter correct Phone Number :");
                    phoneNo = sc.nextLine();
                }
            }
            String id = "admin" + (adminid++);
            admin = new Admin(name, email, DOB, phoneNo, id);
        }

        adminLoop: while (true) {
            System.out.print(
                    RESET + PURPLE
                            + "\n╔════════════════════════════╗\n║         Admin Menu         ║\n╠════════════════════════════╣\n║1.Courier Details           ║\n║2.Delivery Agent            ║\n║3.Log Out                   ║\n╚════════════════════════════╝\n"
                            + RESET + YELLOW);
            int choice = 0;
            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println(RESET + RED + "Invalid choice" + RESET + YELLOW);
                    continue;
                }
            }
            if (choice == 1) {
                courierloop: while (true) {
                    System.out.print(
                            RESET + PURPLE
                                    + "\n╔════════════════════════════╗\n║        Admin/Courier       ║\n╠════════════════════════════╣\n║1.View Couriers             ║\n║2.Delivered Courier         ║\n║3.Waiting Courier           ║\n║4.Courier in Process        ║\n║5.Back                      ║\n╚════════════════════════════╝\n"
                                    + RESET + YELLOW);
                    while (true) {
                        try {
                            System.out.print("Enter your choice: ");
                            choice = sc.nextInt();
                            sc.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println(RESET + RED + "Invalid choice" + RESET + YELLOW);
                            continue;
                        }
                    }

                    if (choice == 1) {
                        if (!(courierManagement.isEmpty())) {
                            String Detail = "";
                            for (int i = 0; i < courierManagement.size(); i++) {
                                Detail += courierManagement.get(i).courierDetail() + "\n";
                            }
                            System.out.println(Detail);
                        } else {
                            System.out.println("No Couriers Available");
                        }
                    } else if (choice == 2) {
                        if (!(courierManagement.isEmpty())) {
                            String Details = "";
                            for (int i = 0; i < courierManagement.size(); i++) {
                                if (courierManagement.get(i).courierStatus.equalsIgnoreCase("Delivered")) {
                                    Details += courierManagement.get(i).courierDetail() + "\n";
                                }
                            }
                            if (Details.equals("")) {
                                System.out.println("No Couriers Available");
                            } else {
                                System.out.println(Details);
                            }
                        } else {
                            System.out.println("No Couriers Available");
                        }
                    } else if (choice == 3) {
                        if (!(courierManagement.isEmpty())) {
                            String Details = "";
                            for (int i = 0; i < courierManagement.size(); i++) {
                                if (courierManagement.get(i).courierStatus.equalsIgnoreCase("Waiting")) {
                                    Details += courierManagement.get(i).courierDetail() + "\n";
                                }
                            }
                            if (Details.equals("")) {
                                System.out.println("No Couriers Available");
                            } else {
                                System.out.println(Details);
                            }
                        } else {
                            System.out.println("No Couriers Available");
                        }
                    } else if (choice == 4) {
                        if (!(courierManagement.isEmpty())) {
                            String Details = "";
                            for (int i = 0; i < courierManagement.size(); i++) {
                                if (courierManagement.get(i).courierStatus.equalsIgnoreCase("Processing")) {
                                    Details += courierManagement.get(i).courierDetail() + "\n";
                                }
                            }
                            if (Details.equals("")) {
                                System.out.println("No Couriers Available");
                            } else {
                                System.out.println(Details);
                            }
                        } else {
                            System.out.println("No Couriers Available");
                        }
                    } else if (choice == 5) {
                        break courierloop;
                    } else {
                        System.out.println(RESET + RED + "You Enter a invalid choice." + RESET);
                    }
                }
            } else if (choice == 2) {
                coustomerloop: while (true) {
                    System.out.print(
                            RESET + PURPLE
                                    + "\n╔══════════════════════════════════════╗\n║         Admin/Delivery Agent         ║\n╠══════════════════════════════════════╣\n║1.View All Delivery Agent             ║\n║2.Add Delivery Agent                  ║\n║3.Assign courier to Delivery Agent    ║\n║4.Remove Delivery Agent               ║\n║5.Back                                ║\n╚══════════════════════════════════════╝\n"
                                    + RESET + YELLOW);
                    while (true) {
                        try {
                            System.out.print("Enter your choice: ");
                            choice = sc.nextInt();
                            sc.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println(RESET + RED + "Invalid choice" + RESET + YELLOW);
                            continue;
                        }
                    }
                    if (choice == 1) {
                        String detail = "";
                        for (int i = 0; i < agents.size(); i++) {
                            detail += "\n"+(i + 1) + "." + agents.get(i).displayAgent() + "\n";
                        }
                        if (detail.equals("")) {
                            System.out.println("No Delivery Agent Available.");
                        } else {
                            System.out.println(detail);
                        }
                    } else if (choice == 2) {
                        System.out.print("Enter a name :");
                        String name = sc.nextLine();
                         System.out.print("Enter a City                 :");
                        String city=sc.nextLine();
                        System.out.print("Enter a Birth Year           :");
                        String DOB = sc.nextLine();
                       
                        int currentyear = 2025;
                        if ((currentyear - 18) > Integer.parseInt(DOB)&& (currentyear - 60 + 18) < Integer.parseInt(DOB)) {
                            System.out.print("Enter a Phone Number         :");
                            String phoneNo = sc.nextLine();
                            number: while (true) {
                                if (phoneNo.length() == 10) {
                                    break number;
                                } else {
                                    System.out.print("Enter correct Phone Number   :");
                                    phoneNo = sc.nextLine();
                                }
                            }
                            String id = "Da" + (++noId);
                            email = "agent@" + name + "gmail.com";
                            DeliverAgent a = new DeliverAgent(name, email, DOB, phoneNo, id,city);
                            admin.addDeliveryAgent(a);
                            agents.add(a);
                            System.out.println(RESET + GREEN + "Delivery Agent Added Successfully!" + RESET + YELLOW
                                    + "\nDelivery agent mail Id : " + email);
                            file.addDeliveragentLog(name, email, "-");
                        } else {
                            System.out.println(
                                    RESET + RED + "Invalid year (or) Your Age is not valid for job" + RESET + YELLOW);
                        }

                    } else if (choice == 3) {
                        String courierId = "couriers : ";
                        int count = -5;
                        for (int i = 0; i < courierManagement.size(); i++) {
                            if (courierManagement.get(i).courierStatus.equalsIgnoreCase("Processing")) {
                                count = i;
                                courierId += "\n" + "Courier Id : " + courierManagement.get(i).courierId +
                                        "\nSend By : "
                                        + courierManagement.get(i).customerDetail.name + "\nCourier Weight : "
                                        + (courierManagement.get(i).courierWight)+"\nLocation : "+courierManagement.get(i).receiverAddress.city
                                        + "\nInside the Courier : " + courierManagement.get(i).courierDetail + "\n";
                            }
                        }
                        if (count >= 0) {
                            courierId += "\nEnter the Courier Id: ";
                            System.out.print(RESET + YELLOW + courierId);
                            String id = sc.nextLine();
                            courier c = courierManagement.get(0);
                            for (int i = 0; i < courierManagement.size(); i++) {
                                if (courierManagement.get(i).courierId.equalsIgnoreCase(id)) {
                                    c = courierManagement.get(i);
                                    for(int j=0;j<customerList.size();j++){
                                for(int k=0;k<customerList.get(j).couriers.size();k++){
                                   if(courierManagement.get(i).courierId.equals(customerList.get(j).couriers.get(k).courierId)){
                                        customerList.get(j).couriers.get(k).courierStatus="Delivered";
                                   }
                                }
                            }
                                }
                            }
                            String agentId = "\nAgents: \n";
                            for (int i = 0; i < admin.listOfAgent.size(); i++) {
                                agentId += "\nName : " + admin.listOfAgent.get(i).name + "( Delivery Agent Id : "
                                        + admin.listOfAgent.get(i).agentId + " )\nLocation : "+admin.listOfAgent.get(i).agentLocation+"\n";
                            }
                            agentId += "\nEnter the Delivery agent Id : ";
                            System.out.print(agentId);
                            String Agentid = sc.nextLine();
                            admin.assigncourierToDeliverAgent(c, Agentid);
                            for (int i = 0; i < admin.listOfAgent.size(); i++) {
                                if (admin.listOfAgent.get(i).agentId.equalsIgnoreCase(Agentid)) {
                                    System.out.println(RESET+GREEN+c.name + " courier is assigned to "
                                            + admin.listOfAgent.get(i).name+RESET+YELLOW);
                                }
                            }

                        } else {
                            System.out.println("No courier to Assign.");
                        }

                    } else if (choice == 4) {
                        int count = -4;
                        String agentId = "Agents:";
                        for (int i = 0; i < admin.listOfAgent.size(); i++) {
                            agentId += "\nName : " + admin.listOfAgent.get(i).name + "( Delivery Agent Id : "
                                    + admin.listOfAgent.get(i).agentId + " )\n";
                            count = i;
                        }
                        agentId += "\nEnter the Delivery agent Id : ";
                        if (count >= 0) {
                            System.out.print(agentId);
                            String Agentid = sc.nextLine();
                            admin.removeDeliveryAgent(Agentid);
                            for (int i = 0; i < agents.size(); i++) {
                                if (agents.get(i).agentId.equalsIgnoreCase(Agentid)) {
                                    agents.remove(i);
                                    break;
                                }
                            }
                            System.out.println(RESET + GREEN + "Agent removed Successfully" + RESET);
                        } else {
                            System.out.println(YELLOW + "No Delivery agent");
                        }
                    } else if (choice == 5) {
                        break coustomerloop;
                    } else {
                        System.out.println(RESET + RED + "You Enter a invalid choice." + RESET);
                    }
                }
            } else if (choice == 3) {
                break adminLoop;
            } else {
                System.out.println(RESET + RED + "Please enter a valid option." + RESET);
            }

        }
    }

    void agent(String mail) {

        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String PURPLE = "\u001B[35m";
        String CYAN = "\u001B[36m";
        Scanner sc = new Scanner(System.in);
        String Name = "";
        DeliverAgent a = agents.get(0);
        for (int i = 0; i < agents.size(); i++) {
            if (agents.get(i).email.equalsIgnoreCase(mail)) {
                Name = agents.get(i).name;
                a = agents.get(i);
                break;
            }
        }
        // System.out.println(YELLOW+"Delivery Agent Name: " + Name);

        Agent: while (true) {
            System.out.print(
                    RESET + PURPLE
                            + "\n╔═════════════════════════════════╗\n║       Delivery Agent Menu       ║\n╠═════════════════════════════════╣\n║1.Display couriers               ║\n║2.Display Delivered couriers     ║\n║3.Display Waiting couriers       ║\n║4.Update courier Status          ║\n║5.Log Out                        ║\n╚═════════════════════════════════╝\n"
                            + RESET + YELLOW);
            int choice = 0;
            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println(RESET + RED + "Invalid choice" + RESET + YELLOW);
                    continue;
                }
            }
            if (choice == 1) {
                System.out.println("------------------------------");
                System.out.println(a.allcourier());
                System.out.println("------------------------------");
            } else if (choice == 2) {
                System.out.println("------------------------------");
                System.out.println(a.Deliveredcourier());
                System.out.println("------------------------------");
            } else if (choice == 3) {
                System.out.println("------------------------------");
                System.out.println(a.Waitingcourier());
                System.out.println("------------------------------");
            } else if (choice == 4) {
                int count = -4;
                for (int i = 0; i < a.couriers.size(); i++) {
                    if (a.couriers.get(i).courierStatus.equalsIgnoreCase("Waiting")) {
                        count = i;
                    }
                }
                if (count>=0) {
                    String detail = "couriers: ";
                    for (int i = 0; i < a.couriers.size(); i++) {
                        if (a.couriers.get(i).courierStatus.equalsIgnoreCase("Waiting")) {
                            detail += a.couriers.get(i).courierId;
                        }
                    }
                    System.out.println(detail);
                    System.out.print("Enter courier Id: ");
                    String id = sc.nextLine();
                    a.updatecourierStatus(id);
                    int index = 0;
                    for (int i = 0; i < courierManagement.size(); i++) {
                        if (courierManagement.get(i).courierId.equals(id)) {
                            index = i;
                            for(int j=0;j<customerList.size();j++){
                                for(int k=0;k<customerList.get(j).couriers.size();k++){
                                   if(courierManagement.get(i).courierId.equals(customerList.get(j).couriers.get(k).courierId)){
                                        customerList.get(j).couriers.get(k).courierStatus="Delivered";
                                   }
                                }
                            }
                        }
                    }
                    courierManagement.get(index).courierStatus = "Delivered";
                    System.out.print(RESET+GREEN+courierManagement.get(index).courierId+"status changed sueecssfully!."+RESET+YELLOW);
                }
                else {
                    System.out.println("------------------------------\nNo Couriers Available\n------------------------------");
                }

            } else if (choice == 5) {
                break Agent;
            } else {
                System.out.println(RESET + RED + "Please enter a valid option." + RESET);
            }
        }
    }

    void customer(String email) {

        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[34m";
        String PURPLE = "\u001B[35m";
        String CYAN = "\u001B[36m";
        Scanner sc = new Scanner(System.in);
        int value = -1;
        // int customerid = customerList.size();
        Customer c = new Customer(null, null, null, null, null, null);
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).email.equalsIgnoreCase(email)) {
                value = i;
            }
        }
        c = customerList.get(value);
        customerLoop: while (true) {
            System.out.print(
                    RESET + PURPLE
                            + "\n╔════════════════════════════╗\n║       Customer Menu        ║\n╠════════════════════════════╣\n║1.Add courier               ║\n║2.Courier Details           ║\n║3.Track Courier             ║\n║4.Log Out                   ║\n╚════════════════════════════╝\n"
                            + RESET + YELLOW);
            int choice = 0;
            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    sc.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println(RESET + RED + "Invalid choice" + RESET + YELLOW);
                    continue;
                }
            }
            if (choice == 1) {
                System.out.print("You want to change your Address(y/n) :");
                String ans = sc.nextLine();
                if (ans.equalsIgnoreCase("y")) {
                    System.out.print("Enter your Details \nDoor Number                          :");
                    String doorNumber = sc.nextLine();
                    System.out.print("Street                               :");
                    String streetName = sc.nextLine();
                    System.out.print("City                                 :");
                    String city = sc.nextLine();
                    System.out.print("State                                :");
                    String state = sc.nextLine();
                    System.out.print("Pincode                              :");
                    String pincode = sc.nextLine();
                    pin: while (true) {
                        if (pincode.length() == 6) {
                            break pin;
                        } else {
                            System.out.print("Enter correct Pincode                :");
                            pincode = sc.nextLine();
                        }
                    }
                    Address address = new Address(doorNumber, streetName, city, state, pincode);
                    c.changeAddress(address);
                } else if (ans.equalsIgnoreCase("n")) {

                }
                System.out.print("Enter receiver Details\nName                                 :");
                String name = sc.nextLine();
                System.out.print("Phone Number                         :");
                String receiverPhoneNumber = sc.nextLine();
                number: while (true) {
                    if (receiverPhoneNumber.length() == 10) {
                        break number;
                    } else {
                        System.out.print("Enter correct Phone Number           :");
                        receiverPhoneNumber = sc.nextLine();
                    }
                }
                System.out.print("Door Number                          :");
                String doorNumber = sc.nextLine();
                System.out.print("Street                               :");
                String streetName = sc.nextLine();
                System.out.print("City                                 :");
                String city = sc.nextLine();
                System.out.print("State                                :");
                String state = sc.nextLine();
                System.out.print("Pincode                              :");
                String pincode = sc.nextLine();
                pin: while (true) {
                    if (pincode.length() == 6) {
                        break pin;
                    } else {
                        System.out.print("Enter 6 digit Pincode                :");
                        pincode = sc.nextLine();
                    }
                }
                Address receiverAddress = new Address(doorNumber, streetName, city, state,
                        pincode);
                System.out.print("Enter the courier Weight(grams)      :");
                double courierWight = sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter the Details About the courier  :");
                String courierDetail = sc.nextLine();
                int count = 0;
                System.out.print("Payment methods are" + RESET + PURPLE + "(" + RESET + YELLOW
                        + "1.Upi,2.PhonePay,3.Gpay,4.Cash" + RESET + PURPLE + ")" + RESET + YELLOW + "\n");
                while (true) {
                    try {
                        System.out.print("Enter a payment method number        :");
                        count = sc.nextInt();
                        sc.nextLine();
                        if (count <= 4 && count > 0) {
                            break;

                        }
                    } catch (Exception e) {
                        System.out.println(RESET + RED + "Invalid number." + RESET + YELLOW);
                        sc.nextLine();
                    }
                }

                String Paymethod = "";
                if (count == 1) {
                    Paymethod = "Upi";
                } else if (count == 2) {
                    Paymethod = "PhonePay";
                } else if (count == 3) {
                    Paymethod = "Gpay";
                } else if (count == 4) {
                    Paymethod = "Cash";
                }
                int length = courierManagement.size();
                courier courier = new courier(("cu" + (++length)), c, receiverAddress, name,
                        receiverPhoneNumber, courierWight, courierDetail, Paymethod);

                c.addcourier(courier);

                courierManagement.add(courier);
                System.out.println(RESET + GREEN + "Courier successfully added!\n" + RESET);
                System.out.println(RESET + CYAN + courier.Bill() + RESET + YELLOW);

            } else if (choice == 2) {
                System.out.println("------------------------------");
                System.out.println(c.courierDetails());
                System.out.println("------------------------------");
            } else if (choice == 3) {
                System.out.println("------------------------------");
                System.out.println(c.courierTracking());
                System.out.println("------------------------------");
            } else if (choice == 4) {
                break customerLoop;
            } else {
                System.out.println(RESET + RED + "Please enter a valid option." + RESET);
            }
        }
    }
}
