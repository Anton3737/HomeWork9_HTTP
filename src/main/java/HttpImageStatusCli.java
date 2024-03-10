import java.io.IOException;
import java.util.Scanner;

public class HttpImageStatusCli {
    static void askStatus() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter HTTP status code");
        int inputCode = scanner.nextInt();

        if (inputCode < 100 && inputCode > 599) {
            System.err.println("Please enter valid number");
        }
        try {
            HttpStatusImageDownloader.downloadStatusImage(inputCode);
            System.out.println("Image have on site and successful download");

        } catch (Exception e) {
            System.err.println("There is not image for HTTP status " + inputCode);
        }

    }


    public static void main(String[] args) throws IOException {
        for (; ; ) {
            HttpImageStatusCli.askStatus();
        }
    }
}


//Завдання №3 - створи CLI
//        Напиши клас HttpImageStatusCli. У цього класу має бути один метод:
//        void askStatus()

//        Коли викликається цей метод, то програма повинна:
//        запитати у юзера код статусу (наприклад, Enter HTTP status code)
//        юзер вводить в консоль код статусу (наприклад, 200)
//        програма перевіряє, чи є картинка для цього статусу на сайті https://http.cat,
//        і якщо є - то скачує цю картинку. Якщо ж картинки немає - виводить в консоль фразу
//        There is not image for HTTP status <CODE> (замість <CODE> підставляється код статусу, що ввів користувач)
//        якщо користувач вводить некоректне число (наприклад, test) - програма має вивести фразу Please enter valid number
//        Використай клас HttpStatusImageDownloader з попереднього завдання.