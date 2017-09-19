package utilities;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    public static List<List<PhotosDto>> getPhotosListsForComparison(List<PhotosDto> photosByMarsTime, List<PhotosDto> photosByEarthTime, int numberOfItemsToCompare) {


        Map<Integer, PhotosDto> marsPhotosMap =
                photosByMarsTime.stream().collect(Collectors.toMap(PhotosDto::getId, photo -> photo));

        Map<Integer, PhotosDto> earthPhotosMap =
                photosByEarthTime.stream().collect(Collectors.toMap(PhotosDto::getId, photo -> photo));


        List<PhotosDto> selectedPhotosFromMarsForComparing = new ArrayList<>();
        List<PhotosDto> selectedPhotosFromEarcthForComparing = new ArrayList<>();

        int n = 0;
        for (Map.Entry<Integer, PhotosDto> photo : marsPhotosMap.entrySet()) {

            if (earthPhotosMap.containsKey(photo.getKey())) {

                selectedPhotosFromMarsForComparing.add(photo.getValue());
                selectedPhotosFromEarcthForComparing.add(earthPhotosMap.get(photo.getKey()));

                n++;

            }

            if (n == numberOfItemsToCompare) {

                selectedPhotosFromEarcthForComparing.sort(Comparator.comparing(PhotosDto::getId));
                selectedPhotosFromMarsForComparing.sort(Comparator.comparing(PhotosDto::getId));
                break;
            }

        }


        List<List<PhotosDto>> photosListsForComparison = new ArrayList();

        photosListsForComparison.add(selectedPhotosFromEarcthForComparing);
        photosListsForComparison.add(selectedPhotosFromMarsForComparing);


        return photosListsForComparison;


    }

    public static List<String> getListOfAvailibleCameras(List<PhotosDto> photos) {

        return photos.get(1).getRover().getCameras()
                .stream().map(CameraDto::getName).collect(Collectors.toList());

    }

    public static Map<String, Integer> getNemberOfPhotosMadeByEachCamera(List<String> cameras, RestService service, String sol) {


        Map<String, Integer> numberOfPhotosMadeByEachCamera = new HashMap<>();

        cameras.forEach(camera -> {

            numberOfPhotosMadeByEachCamera.put(camera, service.getPhotos(sol, camera).size());


        });


        return numberOfPhotosMadeByEachCamera.entrySet().stream().sorted(Map.Entry.comparingByValue()).
                collect(Collectors.toMap( Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2,
                        LinkedHashMap::new));


    }


}
