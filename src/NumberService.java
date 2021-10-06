import java.util.Map;
import java.util.TreeMap;

class NumberService {

    private final static TreeMap < Integer, String > latString = new TreeMap<>();

    static {
        latString.put(1, "I");
        latString.put(4, "IV");
        latString.put(5, "V");
        latString.put(9, "IX");
        latString.put(10, "X");
        latString.put(40, "XL");
        latString.put(50, "L");
        latString.put(90, "XC");
        latString.put(100, "C");
    }

    static Number parseAndValidate(String symbol) throws Exception {

        int value;
        NumberType type;

        // проверяем какой тип у числа
        try {
            value = Integer.parseInt(symbol);
            type = NumberType.ARAB;
        }catch (NumberFormatException e) {
            // переводим римские числа в арабские
            value = toArabicNumber(symbol);

            //записываем тип
            type = NumberType.LAT;
        }

        // число у нас должно быть в диапозоне от 1 до 10
        if (value < 1 || value > 10) {
            throw new Exception("Неподходящее значение числа(ел), используйте числа от 1 до 10 включительно");
        }


        return new Number(value, type);
    }

    static Number parseAndValidate(String symbol, NumberType type) throws Exception {

        Number number = parseAndValidate(symbol);
        if (number.getType() != type) {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        }

        return number;
    }

    // переводи латинский символ в цифру
    private static int letterToNumber(char letter) {

        int result = -1;

        for (Map.Entry < Integer, String > entry: latString.entrySet()) {
            if (entry.getValue().equals(String.valueOf(letter))) result = entry.getKey();
        }
        return result;
    }

    // перевод из арабского числа в латинское число
    static String toLatNumber(int number) {

        int i = latString.floorKey(number);

        if (number == i) {
            return latString.get(number);
        }
        return latString.get(i) + toLatNumber(number - i);
    }

    // перевод из латинского числа в арабское число
    static int toArabicNumber(String latin) throws Exception {
        int result = 0;

        int i = 0;
        while (i < latin.length()) {
            char letter = latin.charAt(i);
            int num = letterToNumber(letter);

            if (num < 0) throw new Exception("Неверный латинский символ");

            i++;
            if (i == latin.length()) {
                result += num;
            }else {
                int nextNum = letterToNumber(latin.charAt(i));
                if(nextNum > num) {
                    result += (nextNum - num);
                    i++;
                }
                else result += num;
            }
        }
        return result;
    }
}