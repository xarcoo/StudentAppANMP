package com.ubaya.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ubaya.studentapp.databinding.PlaneListItemBinding
import com.ubaya.studentapp.model.Plane

class PlaneListAdapter(val planeList:ArrayList<Plane>):RecyclerView.Adapter<PlaneListAdapter.PlaneViewHolder>() {
    class PlaneViewHolder(var binding: PlaneListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaneViewHolder {
        val binding = PlaneListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaneViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return planeList.size
    }

    override fun onBindViewHolder(holder: PlaneViewHolder, position: Int) {
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception ->  exception.printStackTrace() }
        Picasso.get().load(planeList[position].image).into(holder.binding.imageView)

        holder.binding.txtID.text = planeList[position].id
        holder.binding.txtModel.text = planeList[position].model
        holder.binding.txtManufacturer.text = planeList[position].manufacturer
        holder.binding.txtCapacity.text = "Capacity: " + planeList[position].capacity.toString()
        holder.binding.txtMaxSpeed.text = planeList[position].maxSpeed.toString() + " mph"

        var color = ""
        for (i in planeList[position].colors!!) {
            color += i + "\n"
        }
        holder.binding.txtColors.text = color

        holder.binding.txtLength.text = "Length: " + planeList[position].dimensions?.length
        holder.binding.txtWingspan.text = "Wingspan: " + planeList[position].dimensions?.wingspan
        holder.binding.txtHeight.text = "Height: " + planeList[position].dimensions?.height
    }

    fun updatePlaneList(newPlaneList:ArrayList<Plane>) {
        planeList.clear()
        planeList.addAll(newPlaneList)
        notifyDataSetChanged()
    }
}