package utilities;

import com.google.gson.annotations.SerializedName;

public class PhotosDto {

    private Integer id;
    private Integer sol;
    @SerializedName("earth_date")
    private String earthDate;
    @SerializedName("img_src")
    private String imageSrc;
    private CameraDto camera;
    private RoverDto rover;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotosDto)) return false;

        PhotosDto photosDto = (PhotosDto) o;

        if (!getId().equals(photosDto.getId())) return false;
        if (!getSol().equals(photosDto.getSol())) return false;
        if (!getEarthDate().equals(photosDto.getEarthDate())) return false;
        if (!getImageSrc().equals(photosDto.getImageSrc())) return false;
        if (!getCamera().equals(photosDto.getCamera())) return false;
        return getRover().equals(photosDto.getRover());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getSol().hashCode();
        result = 31 * result + getEarthDate().hashCode();
        result = 31 * result + getImageSrc().hashCode();
        result = 31 * result + getCamera().hashCode();
        result = 31 * result + getRover().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PhotosDto{" +
                "id=" + id +
                ", sol=" + sol +
                ", earthDate='" + earthDate + '\'' +
                ", imageSrc='" + imageSrc + '\'' +
                ", camera" + camera.getId() +
                ", rover=" + rover.getId() +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Integer getSol() {
        return sol;
    }

    public String getEarthDate() {
        return earthDate;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public CameraDto getCamera() {
        return camera;
    }

    public RoverDto getRover() {
        return rover;
    }
}
