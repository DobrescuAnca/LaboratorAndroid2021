package ro.example.myapplication.lab4;

public class MovieModel {

    //    @Json(name = "titlu")
    private String Title;
    private String Year;
    private String imdbID;
    private String Poster;

    public MovieModel(String title, String year, String imdbID, String poster) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public String getYear() {
        return Year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getPoster() {
        return Poster;
    }
}
