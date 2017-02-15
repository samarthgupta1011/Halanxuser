package com.samarthgupta.userapphalanx;

/**
 * Created by samarthgupta on 13/02/17.
 */

public class UserInfo {

  private   String email;
   private String password;
   private String mno;
   private String city;
   private String fname;
   private String lname;
    private String icode;

    public UserInfo() {
        //Empty constructor
    }

    public UserInfo(String email, String password ,String fname,String lname, String mno, String city,String icode){

        this.email = email;
        this.password = password;
        this.city = city;
        this.fname = fname;
        this.lname = lname;
        this.mno = mno;
        this.icode = icode;


    }

    public String getEmail() {
        return email;
    }

    public String getIcode() {
        return icode;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public String getCity() {
        return city;
    }

    public String getMno() {
        return mno;
    }

    public String getPassword() {
        return password;
    }
}
