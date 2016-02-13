package fighting.teamsixteen.unithon.interviewproj;

/**
 * Created by Youngdo on 2016-02-13.
 */
public class Recycler_Item_question {
    String number,title, answer;

    public String getTitle() {
        return title;
    }

    public String getAnswer() {
        return answer;
    }

    public String getNumber() {

        return number;
    }
    Recycler_Item_question(String title, String answer, String number){
        this.answer=answer;
        this.title=title;
        this.number=number;
    }
}
