package org.example.rickandmortyapi;

import okhttp3.OkHttpClient;

import java.io.IOException;

public abstract class RequestHTTP {
    final OkHttpClient client;

    public RequestHTTP() {
        this.client = new OkHttpClient();
    }

    public RequestHTTP(OkHttpClient client) {
        this.client = client;
    }

    public abstract String run(String url)throws IOException;

}
