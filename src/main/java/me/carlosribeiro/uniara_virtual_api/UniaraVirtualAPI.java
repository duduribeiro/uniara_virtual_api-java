package me.carlosribeiro.uniara_virtual_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.carlosribeiro.uniara_virtual_api.models.Student;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class UniaraVirtualAPI {
    public String login(String ra, String password) {
        //TODO: move all this shit here to a specific class to handle http requests
        HttpClient httpClient =  HttpClientBuilder.create().build();
        HttpPost postRequest = new HttpPost(
                "https://uniara-virtual-api.herokuapp.com/login"
        );
        try {
            Student student = new Student(ra, password);
            StringEntity input = new StringEntity(student.toJson());

            input.setContentType("application/json");
            postRequest.setEntity(input);

            HttpResponse response = httpClient.execute(postRequest);


            if (response.getStatusLine().getStatusCode() != 201) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            return EntityUtils.toString(response.getEntity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
