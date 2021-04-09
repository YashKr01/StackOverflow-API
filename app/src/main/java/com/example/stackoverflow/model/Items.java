package com.example.stackoverflow.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Items {

    @SerializedName("owner")
    @Expose
    private Owner owner;

    @SerializedName("is_accepted")
    @Expose
    private Boolean isAccepted;

    public Items(Owner owner, Boolean isAccepted) {
        this.owner = owner;
        this.isAccepted = isAccepted;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return owner.equals(items.owner) &&
                isAccepted.equals(items.isAccepted);
    }

}
