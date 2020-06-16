package com.seletivo.promobit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.seletivo.promobit.R
import com.seletivo.promobit.adapter.ListContactsAdapter
import com.seletivo.promobit.binding.FragmentDataBindingComponent
import com.seletivo.promobit.databinding.FragmentFormContactBinding
import com.seletivo.promobit.databinding.FragmentHomeBinding
import com.seletivo.promobit.di.annotation.Injectable
import com.seletivo.promobit.util.async.AppExecutors
import com.seletivo.promobit.util.autoCleared
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val mHomeViewModel: HomeViewModel by viewModels { mViewModelFactory }

    var mDataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)
    var mBinding by autoCleared<FragmentHomeBinding>()
    private var mAdapter by autoCleared<ListContactsAdapter>()

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

        mBinding = binding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner

        mAdapter =ListContactsAdapter(mDataBindingComponent, appExecutors)
        mBinding.recyclerView.adapter = mAdapter

        mHomeViewModel.getAllContacts().observe(viewLifecycleOwner, Observer {
            if (it.data != null && it.data.isNotEmpty()) {
                Timber.d( "data %s", it.data)
                mAdapter.submitList(it.data)
            }
        })

    }

}