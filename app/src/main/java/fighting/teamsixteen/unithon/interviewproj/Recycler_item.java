package fighting.teamsixteen.unithon.interviewproj;

/**
 * Created by Youngdo on 2016-01-19.
 */
public class Recycler_item {
    int image;
    String title;

    int getImage(){
        return this.image;
    }
    String getTitle(){
        return this.title;
    }

    Recycler_item(int image, String title){
        this.image=image;
        this.title=title;
    }
}
