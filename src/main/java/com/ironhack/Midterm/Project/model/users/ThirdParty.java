package com.ironhack.Midterm.Project.model.users;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class ThirdParty extends User{
    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(long id, String name, String hashedKey) {
        super(id, name);
        this.hashedKey = hashedKey;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

}
