package com.example.asmspringboot.entity.enums;

public enum RoadStatus {
    ACTIVE(1), UNDER_CONSTRUCTION(0),UNDER_REPAIR(-1), UNDEFINED(-2);
    private int value;

    RoadStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static RoadStatus of(int value){
        for (RoadStatus roadStatus: RoadStatus.values()
        ) {
            if (roadStatus.getValue() == value) {
                return roadStatus;
            }
        }
        return UNDEFINED;
    }
}
