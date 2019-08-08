package io.andrewedgar.androidarchitecturepractice.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import io.andrewedgar.androidarchitecturepractice.R
import io.andrewedgar.androidarchitecturepractice.databinding.ItemDogBinding
import io.andrewedgar.androidarchitecturepractice.model.Dog
import io.andrewedgar.androidarchitecturepractice.utils.getProgressDrawable
import io.andrewedgar.androidarchitecturepractice.utils.loadImage
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(val dogsList: ArrayList<Dog>) : RecyclerView.Adapter<DogsListAdapter.DogViewHolder>(),
    DogClickListener {


    fun updateDogList(newDogsList: List<Dog>) {
        dogsList.clear()
        dogsList.addAll(newDogsList)
        notifyDataSetChanged()


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {

        //Creates ViewHolder with the ItemDogBinding as a view

        val inflater = LayoutInflater.from(parent.context)


        //uses a special inflater to inflate the view with databinding functionality

        val view = DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)


        return DogViewHolder(view)

    }

    override fun getItemCount(): Int = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {

        //Assigns dog to view. Since the attributes were mapped in the xml layout file, they don't need to be assigned
        // individually here


        holder.view.dog = dogsList[position]
        holder.view.listener = this


    }


    override fun onDogClicked(v: View) {

        //UUid now lives inside view, and can be passed here
        val uuid = v.dogID.text.toString().toInt()
        val action = ListFragmentDirections.actionDetailFragment()
        action.dogUid = uuid
        Navigation.findNavController(v).navigate(action)

    }


    //ItemDogBindingClass is generated when we drop the layout object in the item_dog layout. Since the view variable
    // is now of type ItemDogBinding, the ViewHolder takes the root view of the binding

    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)
}