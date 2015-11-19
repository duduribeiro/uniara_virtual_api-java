package me.carlosribeiro.uniara_virtual_api;

import com.squareup.okhttp.*;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Endpoint;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UniaraVirtualAPITest {
    OkHttpClient client;
    Endpoint endpoint;

    @Before
    public void setUp() {
        client = mock(OkHttpClient.class, RETURNS_DEEP_STUBS);
        endpoint = mock(Endpoint.class);
    }
    @Test
    public void login() throws IOException {
        when(client.newCall(any(Request.class)).execute())
                .thenReturn(new Response.Builder()
                        .request(new Request.Builder()
                                .url("https://uniara-virtual-api.herokuapp.com/login")
                                .build())
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .body(ResponseBody.create(MediaType.parse("text/html"), "winteriscoming"))
                        .build());
        UniaraVirtualAPI api = new UniaraVirtualAPI(client);
        assertEquals("winteriscoming", api.login("jon", "snow"));
    }
}