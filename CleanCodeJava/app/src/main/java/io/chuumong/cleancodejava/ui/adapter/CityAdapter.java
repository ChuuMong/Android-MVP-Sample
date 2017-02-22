package io.chuumong.cleancodejava.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import io.chuumong.cleancodejava.R;
import io.chuumong.cleancodejava.data.model.CityData;

/**
 * Created by JongHunLee on 2017-02-22.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    private final Context context;

    private final OnItemClickListener listener;

    private final List<CityData> cityDatas;

    public CityAdapter(OnItemClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
        this.cityDatas = new ArrayList<>();
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_list, parent, false));
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(cityDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return cityDatas.size();
    }

    public void add(List<CityData> cityDatas) {
        this.cityDatas.clear();
        this.cityDatas.addAll(cityDatas);
        notifyDataSetChanged();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCity, tvHotel;
        private ImageView ivBackground;

        CityViewHolder(View itemView) {
            super(itemView);

            tvCity = (TextView) itemView.findViewById(R.id.text_city);
            tvHotel = (TextView) itemView.findViewById(R.id.text_hotel);
            ivBackground = (ImageView) itemView.findViewById(R.id.image_background);

        }

        void bind(CityData data) {
            tvCity.setText(data.getName());
            tvHotel.setText(data.getDescription());
            Glide.with(context)
                    .load(data.getBackground())
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .into(ivBackground);

            itemView.setOnClickListener(view -> listener.onClick(data));
        }
    }


    public interface OnItemClickListener {

        void onClick(CityData Item);
    }
}
