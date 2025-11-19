// package projectCode;

import java.io.Serializable;

public class person implements Serializable{
    private static final long serialVersionUID = 1L;
    String name;
    String email;
    String dateOfBirth;
    String PhoneNumber;
    person(String name,String email,String DOB,String phoneNo){
        this.name=name;
        this.email=email;
        dateOfBirth=DOB;
        PhoneNumber=phoneNo;
    }
    String displayPersonDetail(){
        return "Name: "+name+"\nE-mail: "+email+"\nDate Of Birth: "+dateOfBirth+"\nPhone Number: "+PhoneNumber;
    }
}
