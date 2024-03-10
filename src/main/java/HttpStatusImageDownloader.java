import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

public class HttpStatusImageDownloader {
    static void downloadStatusImage(int code) throws IOException {
        String codeLink = HttpStatusChecker.getStatusImage(code);

        String imgDirectoryPathForSave = "src/main/img/";

        String tempFilePath = imgDirectoryPathForSave + "catCode" + code + ".jpg";

        try (InputStream inputStream = new URL(codeLink).openStream(); OutputStream outputStream = new FileOutputStream(tempFilePath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Cat not found " + code);
        }

    }


    public static void main(String[] args) throws IOException {
        downloadStatusImage(2103);
    }
}


//Завдання №2 - скачай картинку
//        Напиши клас HttpStatusImageDownloader. Цей клас має мати один метод:
//        void downloadStatusImage(int code). Він приймає код статусу, і якщо для цього коду статусу є картинка - скачує цю картинку,
//        і зберігає її в папці, де була запущена програма. Якщо картинки немає - метод викидає Exception.
//        Використай клас HttpStatusSchecker з попереднього завдання для отримання шляху до картинки та визначення наявності самої картинки.