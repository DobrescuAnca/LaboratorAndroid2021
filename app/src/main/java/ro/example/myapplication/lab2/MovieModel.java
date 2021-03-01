package ro.example.myapplication.lab2;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {

    private String title;
    private String duration;
    private Integer imageId;

    public MovieModel(String title, String duration, Integer imageId) {
        this.title = title;
        this.duration = duration;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }
    public String getDuration() {
        return duration;
    }
    public Integer getImageId() {
        return imageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.title, this.duration, this.imageId.toString()});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public MovieModel(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.title = data[0];
        this.duration = data[1];
        this.imageId = Integer.valueOf(data[2]);
    }

}
