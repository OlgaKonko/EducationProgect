package assertions;

import com.jayway.restassured.response.Response;
import constants.ResponseCode;
import model.pet.ApiResponse;

public class AssertStatusCode {
    public static void assertStatusCodeIsOk(Response response) {
        response
                .then()
                .statusCode(ResponseCode.SUCCESS_CODE.getCode());
    }

    public static void assertStatusCodeIsOk(ApiResponse response) {
        assert response.getCode() == ResponseCode.SUCCESS_CODE.getCode() : "Error - status code is not success";
    }

}
