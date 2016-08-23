package uk.co.virtual1.test.util;

import flexjson.JSONSerializer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Mikhail Tkachenko created on 26.05.16 11:14
 */
public final class LocalHttpClient {
    private JSONSerializer serializer = new JSONSerializer();

    public String send(String uri, Object requestBody) {
        String body = serializer.deepSerialize(requestBody);

        try {
            HttpURLConnection con = createConnection(uri);
            con.addRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("Content-Length", String.valueOf(body.length()));
            con.setRequestMethod("POST");

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.writeBytes(body);
            }
            return readResponse(con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String send(String uri) {
        try {
            HttpURLConnection con = createConnection(uri);
            con.setRequestMethod("GET");
            return readResponse(con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private HttpURLConnection createConnection(String uri) throws IOException {
        URL url = new URL("http://127.0.0.1:9090/api/json" + uri);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(1000 * 30);
        httpURLConnection.setDoOutput(true);
        return httpURLConnection;
    }

    private String readResponse(HttpURLConnection con) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                result.append(inputLine);
            }
        }
        return result.toString();
    }

}