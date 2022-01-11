package org.example.rickandmortyapi;

import com.amazonaws.services.dynamodbv2.xspec.M;
import com.amazonaws.services.lambda.runtime.Context;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;

class HandlerTest {

    @Test
     public void happy_path() throws IOException {

        Handler handler = new Handler();
        GetRequest getRequest = Mockito.mock(GetRequest.class);
        Mockito.when(getRequest.run(Mockito.any())).thenReturn("Una rta");

        String response = (String) handler.handleRequest(getRequest, Mockito.mock(Context.class));

        Mockito.verify(getRequest, Mockito.times(1)).run(handler.getUrl());
        Assertions.assertEquals("Una rta", response);



     }
}