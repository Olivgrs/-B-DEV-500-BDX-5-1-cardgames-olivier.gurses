package com.example.dashboard.Services.AdapterPaypal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.dashboard.Services.PaypalModel.Coffe;
import com.example.dashboard.R;

import java.util.List;

public class CoffeAdapter extends PagerAdapter {

    private Context context;
    private List<Coffe> listCoffe;

    public CoffeAdapter(Context context, List<Coffe> listCoffe) {
        this.context = context;
        this.listCoffe = listCoffe;
    }

    @Override
    public int getCount() {
        return listCoffe.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, container, false);

        ImageView ivImage = view.findViewById(R.id.iv_image);
        ImageView ivDecoration = view.findViewById(R.id.iv_decoration);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvSubtitle = view.findViewById(R.id.tv_subtitle);
        TextView tvDesc = view.findViewById(R.id.tv_desc);
        RelativeLayout relativeBack = view.findViewById(R.id.relative_back);

        ivImage.setImageResource(listCoffe.get(position).getImage());
        ivDecoration.setImageResource(listCoffe.get(position).getDecoration());
        relativeBack.setBackgroundResource(listCoffe.get(position).getBackground());
        tvTitle.setText(listCoffe.get(position).getTitle());
        tvSubtitle.setText(listCoffe.get(position).getSubTitle());
        tvDesc.setText(listCoffe.get(position).getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, ""+listCoffe, Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);

        return view;
    }
}
