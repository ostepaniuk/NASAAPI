package utilities;

import com.google.gson.annotations.SerializedName;

public class CameraDto {

    private Integer id;
    private String name;
    @SerializedName("rover_id")
    private Integer roverID;
    @SerializedName("full_name")
    private String fullName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CameraDto)) return false;

        CameraDto cameraDto = (CameraDto) o;

        if (!getId().equals(cameraDto.getId())) return false;
        if (!getName().equals(cameraDto.getName())) return false;
        if (!getRoverID().equals(cameraDto.getRoverID())) return false;
        return getFullName().equals(cameraDto.getFullName());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getRoverID().hashCode();
        result = 31 * result + getFullName().hashCode();
        return result;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRoverID() {
        return roverID;
    }

    public String getFullName() {
        return fullName;
    }
}
