package io.andrewedgar.androidarchitecturepractice.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation

import io.andrewedgar.androidarchitecturepractice.R
import io.andrewedgar.androidarchitecturepractice.databinding.FragmentDetailBinding
import io.andrewedgar.androidarchitecturepractice.model.Dog
import io.andrewedgar.androidarchitecturepractice.utils.getProgressDrawable
import io.andrewedgar.androidarchitecturepractice.utils.loadImage
import io.andrewedgar.androidarchitecturepractice.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_dog.view.*


class DetailFragment : Fragment() {


    private lateinit var viewModel: DetailViewModel
    private var dogUid = 0



    private lateinit var dataBinding:FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail,container,false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            dogUid = DetailFragmentArgs.fromBundle(it).dogUid

        }


        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUid)


        // receives data passed from the fragment that navigated to this one

        observeViewModel()


    }


    private fun observeViewModel() {
        viewModel.dogLiveData.observe(this, Observer { dog: Dog ->

            dog.let {
                dataBinding.dog = dog

            }

        })
    }

}
