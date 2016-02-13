package fighting.teamsixteen.unithon.interviewproj;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Youngdo on 2016-01-19.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    List<Recycler_item> items;
    int item_layout;

    public RecyclerAdapter(Context context, List<Recycler_item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Recycler_item item = items.get(position);
        Drawable drawable = context.getResources().getDrawable(item.getImage());
        Drawable drawable1 = context.getResources().getDrawable(
                R.drawable.background);
        holder.title.setText(item.getTitle());
        holder.time.setText(item.getTime());
        holder.goal.setText(item.getGoal());
        if(position == 0){
            holder.title.setText("");
            holder.time.setText("");
            holder.goal.setText("");
            holder.linearLayout.setBackground(drawable1);
            holder.image.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.two.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.three.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.linearLayout2.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardview;
        TextView time;
        TextView goal;
        LinearLayout linearLayout, linearLayout2;
        ImageView one, two, three;


        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            cardview = (CardView) itemView.findViewById(R.id.cardView);
            goal = (TextView) itemView.findViewById(R.id.textView);
            time = (TextView) itemView.findViewById(R.id.textView2);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear);
            two = (ImageView) itemView.findViewById(R.id.imageView2);
            three = (ImageView) itemView.findViewById(R.id.imageView3);
            linearLayout2 = (LinearLayout) itemView.findViewById(R.id.linear2);
        }
    }
}