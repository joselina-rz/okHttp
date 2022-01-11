package org.example.rickandmortyapi;

import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.stream.Stream;

class GetRequestTest {
    private final OkHttpClient client = Mockito.mock(OkHttpClient.class);
    private final Response response = Mockito.mock(Response.class);
    private final Call call = Mockito.mock(Call.class);

   @TestFactory
    public Stream<DynamicTest> types_of_errors_to_a_call(){
        return Stream.of(RESOURCE_NOT_FOUND, INVALID_INPUT, UNEXPECTED_ERROR)
                .map(testCase->DynamicTest.dynamicTest(testCase.getCaseName(), ()->{

                    GetRequest getRequest = new GetRequest(client);

                    Mockito.when(client.newCall(Mockito.any())).thenReturn(call);
                    Mockito.when(call.execute()).thenReturn(response);
                    Mockito.when(response.code()).thenReturn(testCase.getCode());

                    Assertions.assertEquals(testCase.getDescription(), getRequest.run("https://unaurl"));

                }));
    }

    private  static class ResponseTypesTestCase{
       private final String caseName;
       private String url;
       private int code;
       private String description;

        public ResponseTypesTestCase(String caseName, String url, int code, String  description) {
            this.caseName = caseName;
            this.url = url;
            this.code = code;
            this.description = description;
        }

        public String getCaseName() {
            return caseName;
        }

        public String getUrl() {
            return url;
        }

        public int getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }
    private static final ResponseTypesTestCase RESOURCE_NOT_FOUND = new ResponseTypesTestCase(
            "RESOURCE_NOT_FOUND",
            "https://rickandmortyapi.com/api/character/0", 404,
            "Resource not found"
    );

    private static final ResponseTypesTestCase INVALID_INPUT = new ResponseTypesTestCase(
            "INVALID_INPUT",
            "https://unaURL", 400,
            "Invalid input"
    );
    private static final ResponseTypesTestCase UNEXPECTED_ERROR = new ResponseTypesTestCase(
            "UNEXPECTED_ERROR",
            "https://R", 500,
            "Unexpected error"
    );
    @Test
    public void resource_found() throws IOException {

        ResponseBody responseBody = Mockito.mock(ResponseBody.class);
        GetRequest getRequest = new GetRequest(client);

        Mockito.when(response.body()).thenReturn(responseBody);
        Mockito.when(client.newCall(Mockito.any())).thenReturn(call);
        Mockito.when(call.execute()).thenReturn(response);
        Mockito.when(responseBody.string()).thenReturn("resource found");

        Assertions.assertEquals("resource found", getRequest.run("https://contenido.com"));
    }
    @Test
    public void call_execute() throws IOException {

        String url = "https://unaurl/";

        ResponseBody responseBody = Mockito.mock(ResponseBody.class);

        GetRequest getRequest = new GetRequest(client);

        Mockito.when(response.body()).thenReturn(responseBody);
        Mockito.when(responseBody.string()).thenReturn("resource found");
        Mockito.when(client.newCall(Mockito.any())).thenReturn(call);
        Mockito.when(call.execute()).thenReturn(response);

        getRequest.run(url);
        ArgumentCaptor <Request> argumentCaptor = ArgumentCaptor.forClass(Request.class);

        Mockito.verify(client, Mockito.times(1)).newCall(argumentCaptor.capture());
        Mockito.verify(call, Mockito.times(1)).execute();

        Assertions.assertEquals(url, argumentCaptor.getValue().url().toString());



    }


}