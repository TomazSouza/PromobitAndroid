package com.seletivo.promobit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.seletivo.promobit.R
import com.seletivo.promobit.databinding.FragmentFormContactBinding
import com.seletivo.promobit.databinding.FragmentHomeBinding
import com.seletivo.promobit.di.annotation.Injectable
import com.seletivo.promobit.util.async.AppExecutors
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val mHomeViewModel: HomeViewModel by viewModels { mViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        val fab: FloatingActionButton = binding.root.findViewById(R.id.fab)
        fab.setOnClickListener {
            findNavController().navigate(R.id.nav_form_contact)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}