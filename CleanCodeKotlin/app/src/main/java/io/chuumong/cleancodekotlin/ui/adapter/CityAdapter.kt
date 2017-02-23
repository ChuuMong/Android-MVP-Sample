package io.chuumong.cleancodekotlin.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import io.chuumong.cleancodekotlin.R
import io.chuumong.cleancodekotlin.data.model.CityData
import io.chuumong.cleancodekotlin.data.model.CityList
import java.util.*

/**
 * Created by JongHunLee on 2017-02-23.
 */

class CityAdapter(val context: Context, val listener: OnItemClickListener) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private val citys = ArrayList<CityData>()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) =
            CityViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_list, parent, false))

    override fun onBindViewHolder(holder: CityViewHolder?, position: Int) {
        holder?.bind(citys[position])
    }

    override fun getItemCount() = citys.size

    fun add(citys: List<CityData>) {
        this.citys.clear()
        this.citys.addAll(citys)
        notifyDataSetChanged()
    }

    inner class CityViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvCity: TextView = itemView.findViewById(R.id.text_city) as TextView
        private val tvHotel: TextView = itemView.findViewById(R.id.text_hotel) as TextView
        private val ivBackground: ImageView = itemView.findViewById(R.id.image_background) as ImageView

        fun bind(data: CityData) {
            tvCity.text = data.name
            tvHotel.text = data.description
            Glide.with(context)
                    .load(data.background)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .centerCrop()
                    .into(ivBackground)

            itemView.setOnClickListener { view -> listener.onClick(data) }
        }
    }

    interface OnItemClickListener {
        fun onClick(data: CityData)
    }
}