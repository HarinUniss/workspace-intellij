package it.oc.gestionefatture;

public class Customer {

    private String fullname;
    private String address;

    public Customer( String fullname , String address ) {
        setFullname(fullname);
        setAddress(address);
    }

    public String getAddress() {
        return address;
    }

    public String getFullname() {
        return fullname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
