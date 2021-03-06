package me.carlosribeiro.uniara_virtual_api;

import com.squareup.okhttp.*;
import me.carlosribeiro.uniara_virtual_api.exceptions.InvalidLoginException;
import me.carlosribeiro.uniara_virtual_api.models.Student;

import java.io.IOException;

public class UniaraVirtualAPI {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client;

    public UniaraVirtualAPI() {
        this(new OkHttpClient());
    }

    public UniaraVirtualAPI(OkHttpClient client) {
        this.client = client;
    }

    public String login(String ra, String password) throws IOException, InvalidLoginException {
        //TODO: move all this shit here to a specific class to handle http requests
        //TODO: treat all possible errors. this is only the happy path.

        Student student = new Student(ra, password);

        RequestBody body = RequestBody.create(JSON, student.toJson());
        Request request = new Request.Builder()
                .url("https://uniara-virtual-api.herokuapp.com/login")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if(response.code() == 400) {
            throw new InvalidLoginException();
        }
        return response.body().string();
    }
}
