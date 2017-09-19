import Models.PhotoMetadata;
import Services.DownloadManager;
import Services.DownloadedPhotosManager;
import Services.PhotosDownloader;
import Services.PhotosPercentageDifference;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.PhotosDto;
import utilities.RestService;
import utilities.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareImagesdataTest {

    // SOLS to retrieve photos by mars time
    private static final int SOLS = 1000;
    // DATE to retrieve photos by earth time (YYYY-MM-DD)
    private static final String DATE = "2015-05-30";

    @Before
    public  void removeAllDownloadedImages() throws IOException {
        File downloadsDirectory = new File("downloads");

        if (downloadsDirectory.exists()) {
            FileUtils.deleteDirectory(downloadsDirectory);
        }
    }


    @Test
    public void CompareDataRetrievedBySolAdnEarthTime() {

        RestService getMarsPhotosRequest = new RestService();
        RestService getEarthPhotosRequest = new RestService();

        List<List<PhotosDto>> photosListsForComparison = Utils.getPhotosListsForComparison(
                getMarsPhotosRequest.getPhotos(SOLS, 0),
                getEarthPhotosRequest.getPhotos(DATE, 0),
                10
        );

        List<PhotosDto> marsPhotos = photosListsForComparison.get(0);

        List<PhotosDto> earthPhotos = photosListsForComparison.get(1);

        Assert.assertTrue("No similar images were selected for comparison in Earth in collection.", !marsPhotos.isEmpty());
        Assert.assertTrue("No similar images were selected for comparison in Mars collection.", !earthPhotos.isEmpty());

        PhotosDownloader earthPhotosDownloader = new PhotosDownloader(marsPhotos);
        earthPhotosDownloader.setSubdir("mars");

        PhotosDownloader marsPhotosDownloader = new PhotosDownloader(earthPhotos);
        marsPhotosDownloader.setSubdir("earth");

        System.out.println("Starting...");
        DownloadManager downloadManager =
                new DownloadManager(
                        Arrays.asList(earthPhotosDownloader, marsPhotosDownloader),
                        () -> {

                            DownloadedPhotosManager earthPhotosManager = new DownloadedPhotosManager("earth");
                            DownloadedPhotosManager marsPhotosManager = new DownloadedPhotosManager("mars");

                            HashMap<String, PhotoMetadata> earthPhotosMetadata = earthPhotosManager.getPhotosMetadata();
                            HashMap<String, PhotoMetadata> marsPhotosMetadata = marsPhotosManager.getPhotosMetadata();

                            comparePhotosCollectionsMetadata(earthPhotosMetadata, marsPhotosMetadata);
                            comparePhotosCollectionsImage(earthPhotosManager.getPhotos(), marsPhotosManager.getPhotos());

                            return null;
                        });

        downloadManager.run();

        for (int i = 0; i < marsPhotos.size(); i++) {

            Assert.assertTrue("images api data does not match:" + " " +
                            "mars photo: " + marsPhotos.get(i).toString() +
                            " " + "earth photo " +
                            earthPhotos.get(i).toString()
                    , marsPhotos.get(i).equals(earthPhotos.get(i)));


        }


    }

    private void comparePhotosCollectionsImage(Map<String, File> earthPhotos, Map<String, File> marsPhotos) throws IOException {
        for (Map.Entry<String, File> earthPhoto : earthPhotos.entrySet()) {
            String earthPhotoKey = earthPhoto.getKey();

            File earthFile = earthPhoto.getValue();
            File marsFile = marsPhotos.get(earthPhotoKey);

            PhotosPercentageDifference ppd = new PhotosPercentageDifference(earthFile, marsFile);

            Assert.assertTrue(earthFile.getName() + " earth file does not match " + marsFile.getName() +
                    " mars file.", ppd.differ());
        }
    }

    /**
     * Compare earth and mars photos metadata
     *
     * @param earthPhotosMetadata
     * @param marsPhotosMetadata
     */
    private void comparePhotosCollectionsMetadata(HashMap<String, PhotoMetadata> earthPhotosMetadata, HashMap<String, PhotoMetadata> marsPhotosMetadata) {
        for (Map.Entry<String, PhotoMetadata> earthPhoto : earthPhotosMetadata.entrySet()) {
            String earthPhotoKey = earthPhoto.getKey();

            PhotoMetadata earthPhotoMetadata = earthPhoto.getValue();
            PhotoMetadata marsPhotoMetadata =   marsPhotosMetadata.get(earthPhotoKey);

            Assert.assertTrue(earthPhotoMetadata.getFileName() + " earth photo metadata does not equal " +
                            marsPhotoMetadata.getFileName() + " mars photo metadata.",
                    marsPhotoMetadata.equals(earthPhotoMetadata));
        }
    }


}
