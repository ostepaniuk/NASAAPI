package utilities;

import com.drew.lang.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestService {

    private static final String API_KEY = "hpBO822ImbhI4dVmezlvSMGWIT3m5IX53BCuNtmr";
    private static final String API = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";

    protected Map<String, String> properties = new HashMap<String, String>();

    public RestService() {
        properties.put("api_key", API_KEY);
    }

    public List<PhotosDto> getPhotos(int sol, int page  ){

        if (sol > 0) {
            properties.put("sol", String.valueOf(sol));
        }

        if (page >= 0) {
            properties.put("page", String.valueOf(page));
        }

        return given().params(properties).
                when().get("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos").
                then().extract().response().getBody().as(PhotosListDto.class).getPhotos();
    }

    public List<PhotosDto> getPhotos(@NotNull String date, int page  ){

        if (page >= 0) {
            properties.put("page", String.valueOf(page));
        }

        properties.put("earth_date", date);

        return given().params(properties).
                when().get("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos").
                then().extract().response().getBody().as(PhotosListDto.class).getPhotos();
    }


    public List<PhotosDto> getPhotos(String sol, String cameraName  ){


            properties.put("camera", String.valueOf(cameraName));


        return given().params(properties).
                when().get("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos").
                then().extract().response().getBody().as(PhotosListDto.class).getPhotos();
    }


    public List<PhotosDto> getPhotos(String sol ){
        properties.put("sol",sol);

        return given().params(properties).
                when().get("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos").
                then().extract().response().getBody().as(PhotosListDto.class).getPhotos();
    }


}



