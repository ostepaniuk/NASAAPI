package Models;

import java.util.HashMap;
import java.util.Map;

public class PhotoMetadata {
    /**
     * Photo's metadata
     */
    private Map<String, String> metadata = new HashMap<>();

    /**
     * File name of a photo
     */
    private String fileName;

    /**
     * Set metadata
     *
     * @param key
     * @param data
     */
    public void setMetadata(String key, String data) {
        this.metadata.put(key, data);
    }

    /**
     * Get metadata
     *
     * @return
     */
    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    /**
     * Get photo's file name
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set photo's file name
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhotoMetadata that = (PhotoMetadata) o;

        return getMetadata() != null ? getMetadata().equals(that.getMetadata()) : that.getMetadata() == null;
    }

    @Override
    public int hashCode() {
        return getMetadata() != null ? getMetadata().hashCode() : 0;
    }
}
