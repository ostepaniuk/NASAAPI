package utilities;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageDownloader {



    public static void image (String url) throws MalformedURLException {


        try {
            try(InputStream in = new URL(url).openStream()){
                try {
                    Files.copy(in, Paths.get("/src"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
