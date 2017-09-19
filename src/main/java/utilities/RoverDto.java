package utilities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoverDto {
    private Integer id;
    private String name;
    @SerializedName("landing_date")
    private String landing_date;
    @SerializedName("launch_date")
    private String launch_date;
    private String status;
    @SerializedName("max_date")
    private String maxDate;
    @SerializedName("max_sol")
    private String maxSol;
    @SerializedName("total_photos")
    int totalPhotos;
    List<CameraDto> cameras;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoverDto)) return false;

        RoverDto roverDto = (RoverDto) o;

        if (!getId().equals(roverDto.getId())) return false;
        if (!getName().equals(roverDto.getName())) return false;
        if (!getLanding_date().equals(roverDto.getLanding_date())) return false;
        if (!getLaunch_date().equals(roverDto.getLaunch_date())) return false;
        if (!getStatus().equals(roverDto.getStatus())) return false;
        if (!getMaxDate().equals(roverDto.getMaxDate())) return false;
        return getMaxSol().equals(roverDto.getMaxSol());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getLanding_date().hashCode();
        result = 31 * result + getLaunch_date().hashCode();
        result = 31 * result + getStatus().hashCode();
        result = 31 * result + getMaxDate().hashCode();
        result = 31 * result + getMaxSol().hashCode();
        return result;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLanding_date() {
        return landing_date;
    }

    public String getLaunch_date() {
        return launch_date;
    }

    public String getStatus() {
        return status;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public String getMaxSol() {
        return maxSol;
    }

    public Integer getTotalPhotos() {
        return totalPhotos;
    }

    public List<CameraDto> getCameras() {
        return cameras;
    }
}
