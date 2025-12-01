// package projectCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Filehandel {
    File dir;
    File customerFile;
    File DeliverAgentFile;
    File AdminFile;
    File courierFile;
    File adminLogFile;
    File CustomerLogFile;
    File DeliveryAgentLogFile;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    BufferedReader br;
    BufferedWriter bw;
    ArrayList<String> email;
    ArrayList<String> name;
    ArrayList<String> pass;

    void createFile() {
        try {
            dir = new File("Manageing Datas");

            if (!dir.exists()) {
                dir.mkdir();
            }
            adminLogFile = new File(dir, "adminLogFile.txt");
            if (!adminLogFile.exists()) {
                adminLogFile.createNewFile();
            }
            CustomerLogFile = new File(dir, "CustomerLogFile.txt");
            if (!CustomerLogFile.exists()) {
                CustomerLogFile.createNewFile();
            }
            DeliveryAgentLogFile = new File(dir, "DeliveryAgentLogFile.txt");
            if (!DeliveryAgentLogFile.exists()) {
                DeliveryAgentLogFile.createNewFile();
            }
            customerFile = new File(dir, "customerFile.dat");
            if (!customerFile.exists()) {
                customerFile.createNewFile();
            }
            DeliverAgentFile = new File(dir, "DeliverAgentFile.dat");
            if (!DeliverAgentFile.exists()) {
                DeliverAgentFile.createNewFile();
            }
            AdminFile = new File(dir, "AdminFile.dat");
            if (!AdminFile.exists()) {
                AdminFile.createNewFile();
            }
            courierFile = new File(dir, "courierFile.dat");
            if (!courierFile.exists()) {
                courierFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    void modifyDeliveragent(int count, String password) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            FileReader rd = new FileReader(DeliveryAgentLogFile);
            br = new BufferedReader(rd);
            String data = br.readLine();
            while (data != null) {
                arr.add(data);
                data = br.readLine();

            }
            FileWriter wr = new FileWriter(DeliveryAgentLogFile);
            bw = new BufferedWriter(wr);
            for (int i = 0; i < arr.size(); i++) {
                if (count - 1 == i) {
                    String datas[] = arr.get(i).split("`~");
                    bw.write(datas[0] + "`~" + datas[1] + "`~" + password);
                } else {
                    bw.write(arr.get(i));
                }
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    void modifyAgent(String email, String password) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            FileReader rd = new FileReader(DeliveryAgentLogFile);
            br = new BufferedReader(rd);
            String data = br.readLine();
            while (data != null) {
                arr.add(data);
                data = br.readLine();

            }
            FileWriter wr = new FileWriter(DeliveryAgentLogFile);
            bw = new BufferedWriter(wr);
            for (int i = 0; i < arr.size(); i++) {
                String datas[] = arr.get(i).split("`~");
                if (datas[1].equalsIgnoreCase(email)) {
                    bw.write(datas[0] + "`~" + datas[1] + "`~" + password);
                } else {
                    bw.write(arr.get(i));
                }
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    void modifyAdminPass(String email, String password) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            FileReader rd = new FileReader(adminLogFile);
            br = new BufferedReader(rd);
            String data = br.readLine();
            while (data != null) {
                arr.add(data);
                data = br.readLine();

            }
            FileWriter wr = new FileWriter(adminLogFile);
            bw = new BufferedWriter(wr);
            for (int i = 0; i < arr.size(); i++) {
                String datas[] = arr.get(i).split("`~");
                if (datas[1].equalsIgnoreCase(email)) {
                    bw.write(datas[0] + "`~" + datas[1] + "`~" + password);
                } else {
                    bw.write(arr.get(i));
                }
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void modifyCustomerlog(String email, String password) {
        ArrayList<String> arr = new ArrayList<>();
        try {
            FileReader rd = new FileReader(CustomerLogFile);
            br = new BufferedReader(rd);
            String data = br.readLine();
            while (data != null) {
                arr.add(data);
                data = br.readLine();

            }
            FileWriter wr = new FileWriter(CustomerLogFile);
            bw = new BufferedWriter(wr);
            for (int i = 0; i < arr.size(); i++) {
                String datas[] = arr.get(i).split("`~");
                if (datas[1].equalsIgnoreCase(email)) {
                    bw.write(datas[0] + "`~" + datas[1] + "`~" + password);
                } else {
                    bw.write(arr.get(i));
                }
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void addDeliveragentLog(String name, String email, String Pass) {
        String data = name + "`~" + email + "`~" + Pass;
        if (DeliveryAgentLogFile == null) {
            createFile();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DeliveryAgentLogFile, true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    void addCustomerLog(String name, String email, String Pass) {
        try {
            String data = name + "`~" + email + "`~" + Pass;
            FileWriter wr = new FileWriter(CustomerLogFile, true);
            bw = new BufferedWriter(wr);
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    void getLogindata() {
        email = new ArrayList<>();
        name = new ArrayList<>();
        pass = new ArrayList<>();
        try {
            FileReader rd = new FileReader(adminLogFile);
            br = new BufferedReader(rd);
            String data = br.readLine();
            while (data != null) {
                String datas[] = data.split("`~");
                email.add(datas[1]);
                name.add(datas[0]);
                pass.add(datas[2]);
                data = br.readLine();
            }
            rd = new FileReader(DeliveryAgentLogFile);
            br = new BufferedReader(rd);
            data = br.readLine();
            while (data != null) {
                String datas[] = data.split("`~");
                email.add(datas[1]);
                name.add(datas[0]);
                pass.add(datas[2]);
                data = br.readLine();

            }
            rd = new FileReader(CustomerLogFile);
            br = new BufferedReader(rd);
            data = br.readLine();
            while (data != null) {
                String datas[] = data.split("`~");
                name.add(datas[0]);
                email.add(datas[1]);
                pass.add(datas[2]);
                data = br.readLine();

            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "138");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    void addInCustomerFile(ArrayList<Customer> c) {

        try {
            FileOutputStream fos = new FileOutputStream(customerFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    ArrayList<Customer> getCustomerFile() {
        ArrayList<Customer> list = new ArrayList<>();
        if (!customerFile.exists() || customerFile.length() == 0) {
            return list;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(customerFile));) {
            list = (ArrayList<Customer>) ois.readObject();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    void addcourierFile(ArrayList<courier> c) {

        try {
            FileOutputStream fos = new FileOutputStream(courierFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    ArrayList<courier> getcourierFile() {
        ArrayList<courier> listc = new ArrayList<>();
        if (!courierFile.exists() || courierFile.length() == 0) {
            return listc;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(courierFile));) {
            listc = (ArrayList<courier>) ois.readObject();
            return listc;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return listc;
    }

    void addagentFile(ArrayList<DeliverAgent> a) {

        try {
            FileOutputStream fos = new FileOutputStream(DeliverAgentFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(a);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    ArrayList<DeliverAgent> getagentFile() {
        ArrayList<DeliverAgent> list = new ArrayList<>();
        if (!DeliverAgentFile.exists() || DeliverAgentFile.length() == 0) {
            return list;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DeliverAgentFile));) {
            list = (ArrayList<DeliverAgent>) ois.readObject();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    void addAdminFile(Admin a) {

        try {
            FileOutputStream fos = new FileOutputStream(AdminFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(a);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    Admin getAdminFile() {
        Admin list = new Admin(null, null, null, null, null);
        if (!AdminFile.exists() || AdminFile.length() == 0) {
            return list;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(AdminFile));) {
            list = (Admin) ois.readObject();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
