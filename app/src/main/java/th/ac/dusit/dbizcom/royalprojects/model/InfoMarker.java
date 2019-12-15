package th.ac.dusit.dbizcom.royalprojects.model;

public class InfoMarker {

    public final String name;
    public final String details;
    public final String imageFileName;
    public final int x;
    public final int y;

    public InfoMarker(String name, String details, String imageFileName, int x, int y) {
        this.name = name;
        this.details = details;
        this.imageFileName = imageFileName;
        this.x = x;
        this.y = y;
    }
}
