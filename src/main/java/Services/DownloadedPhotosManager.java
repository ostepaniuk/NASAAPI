package Services;

import Models.PhotoMetadata;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class DownloadedPhotosManager {

    public static final String SAVE_DIR = "downloads";

    private HashMap<String, PhotoMetadata> photosMetadata = new HashMap<>();

    private File folder;

    public DownloadedPhotosManager(String photosSubdir) throws IOException, ImageProcessingException {
        String pathToFolder = SAVE_DIR + (photosSubdir != null ? "/" + photosSubdir : "");
        this.folder = new File(pathToFolder);

        this.processDownloadedPhotos();
    }

    public HashMap<String, PhotoMetadata> getPhotosMetadata() {
        return this.photosMetadata;
    }

    public Map<String, File> getPhotos() {
        Map<String, File> photos = new HashMap<>();

        for (File photo : this.folder.listFiles()) {
            photos.put(photo.getName(), photo);
        }

        return photos;
    }

    private void processDownloadedPhotos() throws IOException, ImageProcessingException {
        for (final File photo : this.folder.listFiles()) {

            Metadata metadata = ImageMetadataReader.readMetadata(photo);

            PhotoMetadata photoMetadata = new PhotoMetadata();

            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {

                    String description = tag.getDescription();
                    if (description == null) {
                        description = directory.getString(tag.getTagType()) + " (unable to formulate description)";
                    }

                    String key = fixKeyName(directory.getName() + "_" + tag.getTagName());

                    if (key.equals("file_file_name")) {
                        photoMetadata.setFileName(description);
                    }

                    if (key.equals("file_file_modified_date")) {
                        continue;
                    }

                    photoMetadata.setMetadata(key,description);
                }
            }

            this.photosMetadata.put(photoMetadata.getFileName(), photoMetadata);
        }
    }

    private static String fixKeyName(String key) {
        return key.replaceAll(" ", "_").toLowerCase();
    }
}
