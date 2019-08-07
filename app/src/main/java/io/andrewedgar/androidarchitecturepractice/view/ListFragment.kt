package io.andrewedgar.androidarchitecturepractice.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import io.andrewedgar.androidarchitecturepractice.R
import io.andrewedgar.androidarchitecturepractice.model.Dog
import io.andrewedgar.androidarchitecturepractice.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel:ListViewModel
    private val dogsListAdapter=DogsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //gets Instance of ViewModel for view to be kept updated

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        //Gets updated data from Viewmodel
        viewModel.refresh()



        //Gets recyclerView and applies its layout Manager and adapter
        dogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        observeViewModel()

        swipeRefreshLayout.setOnRefreshListener {
            dogsList.visibility= View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel(){

        //Gets new data/state for each view from ViewModel and updates them accordingly


        viewModel.dogs.observe(this, Observer {dogs: List<Dog> ->

            dogs.let {
                dogsList.visibility = View.VISIBLE
                dogsListAdapter.updateDogList(dogs)
            }
        })


        viewModel.dogsLoadError.observe(this, Observer {isError:Boolean->

            isError.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }

        })

        viewModel.loading.observe(this, Observer { isLoading:Boolean->

            isLoading.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    listError.visibility = View.GONE
                    dogsList.visibility = View.GONE
                }
            }
        })
    }




}
