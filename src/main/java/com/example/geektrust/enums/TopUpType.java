package com.example.geektrust.enums;

public enum TopUpType {
    FOUR_DEVICE(50),
    TEN_DEVICE(100);

    private int topUpCost;

    TopUpType(int value) {
        this.topUpCost = value;
    }

    public int getTopUpCost() {
        return this.topUpCost;
    }
}
