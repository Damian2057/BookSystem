package org.example.dao.Storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientStorageTest {

    @Test
    void getClient() {

        ClientStorage clientStorage = new ClientStorage("test");
        clientStorage.getAllElementsFromStorage();
    }
}