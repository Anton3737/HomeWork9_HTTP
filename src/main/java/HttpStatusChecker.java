import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.rmi.ConnectException;


public class HttpStatusChecker {


    public static String getStatusImage(int code) throws IOException {
        String requestQuery = "https://http.cat/status/" + code;

        String resultCat = "";

        try {
            Connection connection = Jsoup.connect(requestQuery);

            Document documents = connection.ignoreContentType(true).get();
            Document document = Jsoup.parse(String.valueOf(documents));

            Elements getCodeTitle = document.getElementsByClass("text-center my-12");
            Elements metaElements = document.select("meta[property=og:image]");

            if (!getCodeTitle.isEmpty() && !metaElements.isEmpty()) {
//                String cleanGetCodeTitle = getCodeTitle.toString().replaceAll("<h1 class=\"text-center my-12\">", "").replaceAll("<!-- --> <!-- -->", " ").replaceAll("</h1>", "");
                String cleanMetaElements = metaElements.toString().replaceAll("<meta property=\"og:image\" content=\"", "").replaceAll("\">", "");

//                resultCat = cleanGetCodeTitle + " " + cleanMetaElements;
                resultCat = cleanMetaElements;
            } else {
                System.out.println("https://http.cat/status/404");
            }

        } catch (HttpStatusException httpStatusException) {
            if (httpStatusException.getStatusCode() == 404) {
                System.out.println("https://http.cat/status/404");
            } else {
                throw new IOException("HTTP error: " + httpStatusException.getStatusCode(), httpStatusException);
            }
        } catch (ConnectException connectException) {
            throw new EOFException(connectException.toString());
        }

        return resultCat;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getStatusImage(302));
    }

}


//https://http.cat/[status_code]
//Завдання №1 - отримай посилання
//        Напиши класс HttpStatusChecker. Цей клас має мати один метод:
//        String getStatusImage(int code). Він приймає код статусу, і повертає посилання на картинку для цього коду.
//        Якщо для відповідного коду картинки немає (сайт https://http.cat повернув 404) - метод викидає Exception.
//        Наприклад, виклик getStatusImage(200) має повернути рядок https://http.cat/200.jpg. А виклик getStatusImage(10000) має викинути виключення, тому що сайт https://http.cat поверне код відповіді 404.
//        Протестуй свою програму, викликаючи її з різними аргументами.

