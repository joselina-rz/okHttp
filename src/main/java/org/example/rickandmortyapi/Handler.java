package org.example.rickandmortyapi;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.IOException;

public class Handler implements RequestHandler <GetRequest, Object> {

    public String getUrl() {
        String url = "https://rickandmortyapi.com/api";
        return url;
    }
    public Handler() {}

    @Override
    public Object handleRequest(GetRequest getRequest, Context context) {
        String response = "";
        try {
            response = getRequest.run(getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
