import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        menu();

        while (true) {

            System.out.println("Input: ");
            String line = scanner.nextLine();

            if (line.equals("exit")) {
                  break;
            }

            try {
                // пробел у нас разделитель между числами и знаком
                String[] symbols = line.split(" ");
                if (symbols.length != 3) throw new Exception("т.к. строка не является математической операцией");
                // первое число
                Number firstNumber = NumberService.parseAndValidate(symbols[0]);
                // второе число
                Number secondNumber = NumberService.parseAndValidate(symbols[2], firstNumber.getType());
                String result = OperationService.calculate(firstNumber, secondNumber, symbols[1]);
                System.out.println("Output: \n" + result);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }

        scanner.close();
    }

    private static void menu() {
        System.out.println("Добро пожаловать в Калькулятор, он работает только с арабскими и римскими цифрами от 1 до 10");
        System.out.println("В калькуляторе есть следующие операции:");
        System.out.println("Сложение(+), Вычитание(-), Умножение(*), Деление(/)");
        System.out.println("Для выхода из программы, введите 'exit'");
    }

}