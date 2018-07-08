import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestRest {
    private static HttpURLConnection con;

    @Test(description = "POST")
    public void postRequestExampleTest() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "qwerty");
        requestBody.put("password", "qwerty1234");

        RequestSpecification request = RestAssured.given();
        request.header("Accept", "application/json, text/*");
        request.body(requestBody.toString());

        io.restassured.response.Response response = request.post("https://ib.rencredit.ru/rencredit.server.portal.app/rest/public/auth/login");

        System.out.println(response.statusLine());

        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Status message " + response.body().asString());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 401);
    }


    @Test
    public void AuthenticationBasics() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "qwerty");
        requestBody.put("password", "qwerty1234");

        RestAssured.baseURI = "https://ib.rencredit.ru/rencredit.server.portal.app/rest/public/auth/login";
        RequestSpecification request = RestAssured.given();
        request.header("Accept", "application/json, text/*");
        request.header("Accept-Encoding", "gzip, deflate, br");
        request.header("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        request.header("Connection", "keep-alive");

        request.header("Host", "ib.rencredit.ru");
        request.header("Origin", "https://ib.rencredit.ru");
        request.header("Refer", "https://ib.rencredit.ru/");
        request.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537." +
                "36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
        request.header("X-Requested-Width", "XMLHttpRequest");
        request.header("X-XSRF-TOKEN", "98f24385-6bb5-4d3c-b628-58ffae1f3aec");
        request.body(requestBody.toString());

        io.restassured.response.Response response = request.post("https://ib.rencredit.ru/rencredit.server.portal.app/rest/public/auth/login?username=asdf&password=qwert");

//        Response response = request.get();     // если расскоментировать это значение и закоментировать предыдущее, то ошибка приходит
        System.out.println("Status code: " + response.getStatusCode());
        System.out.println("Status message " + response.body().asString());


        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 401);
    }


    @Test
    public static void moreTesting() throws IOException {

        String url = "https://ib.rencredit.ru/rencredit.server.portal.app/rest/public/auth/login";
        String urlParameters = "username=qwertyw&password=qwerty1234";
        byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(postData);
            }

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } finally {
            con.disconnect();
        }
    }
}


