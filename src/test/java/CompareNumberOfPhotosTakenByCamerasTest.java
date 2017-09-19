import org.junit.Assert;
import org.junit.Test;
import utilities.PhotosDto;
import utilities.RestService;
import utilities.Utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CompareNumberOfPhotosTakenByCamerasTest {

    // SOLS to retrieve photos by mars time
    private static final String SOLS = "1000";
    // Test fails if camera exceeds a certain limit of photos
    private static final int CAMERA_PHOTOS_LIMIT = 10;

    @Test
    public void compareNumberOfImagesTakenByEachCamera(){

        RestService service = new RestService();
        List<PhotosDto> photos = service.getPhotos(SOLS);
        List<String> availibleCameras = Utils.getListOfAvailibleCameras(photos);



        LinkedHashMap<String, Integer>  numberOfImagesTakenByCameras =
                (LinkedHashMap<String, Integer>) Utils.getNemberOfPhotosMadeByEachCamera(availibleCameras,service, SOLS);

        Map.Entry<String, Integer> previousItem = null;

        for (Iterator<Map.Entry<String, Integer>> i = numberOfImagesTakenByCameras.entrySet().iterator(); i.hasNext();) {
            Map.Entry<String, Integer> currentItem = i.next();

            if (previousItem == null || previousItem.getValue() == 0) {
                previousItem = currentItem;
                continue;
            }

            int result = currentItem.getValue() / previousItem.getValue();

            Assert.assertTrue("Camera " + currentItem.getKey() + " has made "
                    + result + " times more than " + previousItem.getKey(),
                    result <= CAMERA_PHOTOS_LIMIT);
        }


    }



}




