package org.example.rickandmortyapi;

import java.io.IOException;
import okhttp3.*;

public class GetRequest extends RequestHTTP{

    public GetRequest(OkHttpClient client) {
        super(client);
    }

    public GetRequest() {
        super();

    }

    @Override
    public String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {

            System.out.println("code: "+ response.code());

            if (response.code() == 404){
                return Error.RESOURCE_NOT_FOUND.getDescription();
            }
            if (response.code() == 400){
                return Error.INVALID_INPUT.getDescription();
            }
            if (response.code() == 500){
                return Error.UNEXPECTED_ERROR.getDescription();
            }

            return response.body().string();
        }
    }

}