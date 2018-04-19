package ex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lc.computer.R;

import java.util.List;

/**
 * Created by sp on 2018/4/2.
 */
//成绩适配器
public class FruitAdapter extends ArrayAdapter<Fruit>{
    private int resourceID;
    public FruitAdapter(Context context, int textViewReasourceID, List<Fruit> objects){
        super(context,textViewReasourceID,objects);
        resourceID = textViewReasourceID;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Fruit fruit =getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView ==null){
            view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
            viewHolder =new ViewHolder();
            viewHolder.textView1 = (TextView)view.findViewById(R.id.test_id);
            viewHolder.textView2 = (TextView)view.findViewById(R.id.g1);
            viewHolder.textView3 = (TextView)view.findViewById(R.id.g2);
            viewHolder.textView4 = (TextView)view.findViewById(R.id.g3);
            viewHolder.textView5 = (TextView)view.findViewById(R.id.g4);
            viewHolder.textView6 = (TextView)view.findViewById(R.id.gg);
            view.setTag(viewHolder);
        }else {
            view =convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.textView1.setText(fruit.getTestid());
        viewHolder.textView2.setText(fruit.getG1());
        viewHolder.textView3.setText(fruit.getG2());
        viewHolder.textView4.setText(fruit.getG3());
        viewHolder.textView5.setText(fruit.getG4());
        viewHolder.textView6.setText(fruit.getGg());
        return view;
    }
    class ViewHolder{
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
    }

}
