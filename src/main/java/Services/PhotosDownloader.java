package Services;

import Models.PhotoMetadata;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import utilities.PhotosDto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class PhotosDownloader implements Downloadable{

    private List<PhotosDto> photos;
    private String subDir;

    public PhotosDownloader(List<PhotosDto> photos) {
        this.photos = photos;
    }

    public void setSubdir(String subDir) {
        this.subDir = subDir;
    }

    /**
     * Download images
     *
     * @throws IOException
     */
    public void download() throws IOException {
        for (PhotosDto photo : photos) {
            URL photoUrl = new URL(photo.getImageSrc());
            String photoName = FilenameUtils.getName(photoUrl.getPath());

            File file = new File( DownloadedPhotosManager.SAVE_DIR + "/" + (this.subDir != null ? this.subDir + "/": "") + photoName);
            FileUtils.copyURLToFile(photoUrl, file);
        }
    }
}
