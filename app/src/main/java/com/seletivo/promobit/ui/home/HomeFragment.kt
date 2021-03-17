package com.seletivo.promobit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
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
import com.seletivo.promobit.databinding.FragmentHomeBinding
import com.seletivo.promobit.di.annotation.Injectable
import com.seletivo.promobit.enums.OrderBy
import com.seletivo.promobit.util.async.AppExecutors
import com.seletivo.promobit.util.autoCleared
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val mHomeViewModel: HomeViewModel by viewModels { mViewModelFactory }

    private var mDataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    private var mBinding by autoCleared<FragmentHomeBinding>()

    private var mAdapter by autoCleared<ListContactsAdapter>()

    private fun initRecycler() {
        mHomeViewModel.results.observe(viewLifecycleOwner, Observer {
            if (it.data != null && it.data.isNotEmpty()) {
                mBinding.resource = it
                mAdapter.submitList(it.data)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentHomeBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )

        val navController = findNavController()

        val fab: FloatingActionButton = binding.root.findViewById(R.id.fab)
        fab.setOnClickListener {
            navController.navigate(R.id.nav_form_contact)
        }

        mBinding = binding
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.lifecycleOwner = viewLifecycleOwner

        mAdapter = ListContactsAdapter(mDataBindingComponent, appExecutors)
        mBinding.recyclerView.adapter = mAdapter

        mHomeViewModel.query(OrderBy.NAME_ASC)
        initRecycler()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_company_asc -> {
                mHomeViewModel.query(OrderBy.COMPANY_ASC)
                return true
            }
            R.id.action_company_desc -> {
                mHomeViewModel.query(OrderBy.COMPANY_DESC)
                return true
            }
            R.id.action_name_desc -> {
                mHomeViewModel.query(OrderBy.NAME_DESC)
                return true
            }
            R.id.action_name_asc -> {
                mHomeViewModel.query(OrderBy.NAME_ASC)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

}