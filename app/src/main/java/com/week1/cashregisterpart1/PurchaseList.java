package com.week1.cashregisterpart1;

import android.util.Log;

import java.util.ArrayList;

public class PurchaseList {
    private ArrayList<PurchasedProduct> purchases;
    private static PurchaseList instance;

    public PurchaseList() {
            purchases = new ArrayList<>();
    }

    public void addPurchase(PurchasedProduct purchase) {

        purchases.add(purchase);


    }

    public ArrayList<PurchasedProduct> getPurchases() {
        return purchases;
    }

    public static PurchaseList getInstance() {
        if (instance == null) {
            instance = new PurchaseList();
        }
        return instance;
    }
}
