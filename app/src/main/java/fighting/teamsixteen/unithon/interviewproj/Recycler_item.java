package fighting.teamsixteen.unithon.interviewproj;

import android.widget.LinearLayout;

/**
 * Created by Youngdo on 2016-01-19.
 */
public class Recycler_item {
    int image;
    String title, goal, time;
    LinearLayout linearLayout;

    int getImage(){
        return this.image;
    }

    String getTitle(){
        return this.title;
    }

    public String getTime() {
        return time;
    }

    public String getGoal() {

        return goal;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }

    Recycler_item(int image, String title, String time, String goal){
        this.image=image;
        this.title=title;
        this.time=time;
        this.goal=goal;
    }
}
