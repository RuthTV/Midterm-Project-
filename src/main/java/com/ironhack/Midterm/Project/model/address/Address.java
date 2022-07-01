
package com.ironhack.Midterm.Project.model.address;

import javax.persistence.*;

@Embeddable
public class Address {
    private String street;
    private int postCode;

    public Address() {
        }
        public Address(String street, int postCode) {
            this.street = street;
            this.postCode = postCode;
        }


        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public int getPostCode() {
            return postCode;
        }
        public void setPostCode(int postCode) {
            this.postCode = postCode;
        }

}
