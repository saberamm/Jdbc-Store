package model;

public enum Shoes implements Product{
    sport(1000),
    official(1000);
    private int value;

    Shoes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
