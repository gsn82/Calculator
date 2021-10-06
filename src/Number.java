class Number {
    // значение числа
    private int value;
    // тип числа
    private NumberType type;

    Number(int value, NumberType type) {
        this.value = value;
        this.type = type;
    }

    int getValue() {
        return value;
    }

    NumberType getType() {
        return type;
    }
}