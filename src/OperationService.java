public class OperationService {

    public static String calculate(Number first, Number second, String operation) throws Exception {

        int result;

        switch (operation) {
            case "+":
                result = first.getValue() + second.getValue();
                break;
            case "-":
                result = first.getValue() - second.getValue();
                break;
            case "*":
                result = first.getValue() * second.getValue();
                break;
            case "/":
                result = first.getValue() / second.getValue();
                break;
            default:
                throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if (first.getType() == NumberType.LAT) {
            // проверка на отрицательность и 0
            if (result <=0)
                throw new Exception("//т.к. в римской системе нет отрицательных чисел и 0");
                // перевод результата из арабского в латин
                return    NumberService.toLatNumber(result);
        } else return String.valueOf(result);
    }
}