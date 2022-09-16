package com.zukka.stargazers

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zukka.stargazers.network.StargazerProperty
import com.zukka.stargazers.utils.inflate
import kotlinx.android.synthetic.main.user_list_item.view.*


class StargazerListAdapter(private val stargazers: List<StargazerProperty>) : RecyclerView.Adapter<StargazerListAdapter.StargazerHolder>() {

    class StargazerHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var stargazer: StargazerProperty? = null

        fun bindStargazer(stargazer: StargazerProperty) {
            this.stargazer = stargazer
            Picasso.with(view.context).load(stargazer.imgSrcUrl).into(view.user_image)
            view.user_name.text = stargazer.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StargazerHolder {
        val inflatedView = parent.inflate(R.layout.user_list_item, false)
        return StargazerHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: StargazerHolder, position: Int) {
        val itemStargazer = stargazers[position]
        holder.bindStargazer(itemStargazer)
    }

    override fun getItemCount(): Int {
       return stargazers.size
    }
}