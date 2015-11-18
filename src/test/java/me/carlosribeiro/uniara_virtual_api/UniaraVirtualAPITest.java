package me.carlosribeiro.uniara_virtual_api;

import org.junit.Test;

import static org.junit.Assert.*;

public class UniaraVirtualAPITest {
    @Test
    public void login() {
        UniaraVirtualAPI client = new UniaraVirtualAPI();
        assertEquals(client.login("ra", "secret"), "");
        //how mock this shit?
    }
}