package com.example.notkink.mpt_android;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.Date;

/**
 * Created by Notkink on 20.11.2017.
 */

public class BillEntry {
    private String shopName;
    private String billName;
    private Bitmap photo;
    private Date purchaseDate;
    private int guaranteeDuration;
    private GuaranteeUnits guaranteeUnit;

    public String getShopName() {
        return shopName;
    }
    public String getBillName() {return billName;}

    public void setBillName(String billName){this.billName = billName;}

    public void setShopName(String shopName) {this.shopName = shopName;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getGuaranteeDuration() {
        return guaranteeDuration;
    }

    public void setGuaranteeDuration(int guaranteeDuration) {
        this.guaranteeDuration = guaranteeDuration;
    }

    public GuaranteeUnits getGuaranteeUnit() {
        return guaranteeUnit;
    }

    public void setGuaranteeUnit(GuaranteeUnits guaranteeUnit) {
        this.guaranteeUnit = guaranteeUnit;
    }
}
