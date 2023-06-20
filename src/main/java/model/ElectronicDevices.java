package model;

public enum ElectronicDevices {
    tv(2000),
    radio(2000);

    private int value;

    ElectronicDevices(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}