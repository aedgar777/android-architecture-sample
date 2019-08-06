package io.andrewedgar.androidarchitecturepractice.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import io.andrewedgar.androidarchitecturepractice.R
import io.andrewedgar.androidarchitecturepractice.model.Dog
import io.andrewedgar.androidarchitecturepractice.utils.getProgressDrawable
import io.andrewedgar.androidarchitecturepractice.utils.loadImage
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(val dogsList: ArrayList<Dog>) : RecyclerView.Adapter<DogsListAdapter.DogViewHolder>() {


    fun updateDogList(newDogsList: List<Dog>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return DogViewHolder(inflater.inflate(R.layout.item_dog, parent, false))

    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.name.text = dogsList[position].dogBreed
        holder.view.lifespan.text = dogsList[position].lifespan

        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }

        holder.view.imageView.loadImage(
            dogsList[position].imageUrl!!,
            getProgressDrawable(holder.view.imageView.context)
        )

    }


    class DogViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}