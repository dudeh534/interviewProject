package fighting.teamsixteen.unithon.interviewproj;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Youngdo on 2016-02-13.
 */
public class RecyclerAdapter_question extends RecyclerView.Adapter<RecyclerAdapter_question.ViewHolder> {
    Context context;
    List<Recycler_Item_question> items;
    int item_layout;


    public RecyclerAdapter_question(Context context, List<Recycler_Item_question> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_question, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Recycler_Item_question item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.number.setText(item.getNumber());
        holder.answer.setText(item.getAnswer());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CardView cardview;
        TextView answer;
        TextView number;


        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.textView6);
            cardview = (CardView) itemView.findViewById(R.id.cardView1);
            answer = (TextView) itemView.findViewById(R.id.textView7);
            number = (TextView) itemView.findViewById(R.id.textView5);
        }
    }
}
