package client;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import static constants.EndpointConstants.BASE_URL;

public abstract class HttpClient {
    RequestSpecification defaultRequest;

    HttpClient(String URL) {
        defaultRequest = new RequestSpecBuilder().
                setBaseUri(BASE_URL).
                setBasePath(URL).
                setAccept(ContentType.JSON).
                setContentType(ContentType.JSON).
                build();
    }

    public void writeResponseLog(Logger log, Response response) {
        String responseParam = "Status code:  " + response.statusCode() + "\nBody: \n" + response.body().prettyPrint();
        log.info("response is \n" + responseParam);
    }
}
