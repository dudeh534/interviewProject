package fighting.teamsixteen.unithon.interviewproj;

import android.graphics.Color;

/**
 * Created by Youngdo on 2016-01-19.
 */


public class ColorDataItem {

    private String name;
    private int color;

    public ColorDataItem(String name, String color) {
        this.name = name;
        this.color = Color.parseColor(color);
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}