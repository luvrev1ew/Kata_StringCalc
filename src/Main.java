import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите выражение в формате \"Привет\" + \"мир\":");

        String input = scanner.nextLine();

        String res = calc(input);

        if(res.length() > 40){
            res = res.substring(0, 40) + "...";
        }

        System.out.println("\"" + res + "\"");

    }

    public static String calc(String input) throws Exception {
        String [] parts = new String[0];
        char operation;
        operation = input.charAt(0);

        String result = "";

        if(input.contains(" + ")){
            parts = input.split(" \\+ ");
            operation = '+';
        }else if (input.contains(" - ")){
            parts = input.split(" - ");
            operation = '-';
        }else if (input.contains(" * ")){
            parts = input.split(" \\* ");
            operation = '*';
        }else if (input.contains(" / ")){
            parts = input.split(" / ");
            operation = '/';
        }else{
            throw new Exception("Ошибка! Неверная форма записи операции.");
        }

        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].replaceAll("\"", "");
        }

        String str1 = parts[0];
        if(str1.length() > 10){
            throw new NumberFormatException("Ошибка! Калькулятор может принимать строки длиной неболее 10 символов.");
        }if (str1.matches("\\d+")){
            throw new NumberFormatException("Ошибка! Первым аргументом выражения должна быть строка.");
        }
        String str2 = parts[1];
        if(str2.length() > 10){
            throw new NumberFormatException("Ошибка! Калькулятор может принимать строки длиной неболее 10 символов.");
        }

        if (operation == '+'){
            result = str1 + str2;
        }else if (operation == '-'){
            result = str1.replace(str2, "");
            if (!str1.contains(str2)){
                return parts[0];
            }
        }else if (operation == '*'){
            if (!str2.matches("\\d+")){
                throw new NumberFormatException("Ошибка! Вторым аргументов выражения должно быть число от 1 до 10.");
            }
            int times = Integer.parseInt(str2);
            if (times < 1 || times > 10){
                throw new NumberFormatException("Ошибка! Калькулятор может принимать только числа от 1 до 10 включительно.");
            }
            for (int i = 0; i < times; i++) {
                result = result + str1;
            }
        }else if (operation == '/'){
            if (!str2.matches("\\d+")){
                throw new NumberFormatException("Ошибка! Вторым аргументов выражения должно быть число от 1 до 10.");
            }
            int times = Integer.parseInt(str2);
            if (times < 1 || times > 10){
                throw new NumberFormatException("Ошибка! Калькулятор может принимать только числа от 1 до 10 включительно.");
            }
            result = str1.substring(0, str1.length() / times);
        }
        return result;
    }
}