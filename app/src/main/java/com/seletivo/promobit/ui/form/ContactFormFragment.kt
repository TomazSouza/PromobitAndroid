package com.seletivo.promobit.ui.form

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.seletivo.promobit.R
import com.seletivo.promobit.databinding.FragmentFormContactBinding
import com.seletivo.promobit.db.ContactObservable
import com.seletivo.promobit.di.annotation.Injectable
import com.seletivo.promobit.enums.Status
import com.seletivo.promobit.util.async.AppExecutors
import javax.inject.Inject

class ContactFormFragment : Fragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var mContactObservable: ContactObservable

    private val mContactViewModel: ContactViewModel by viewModels { mViewModelFactory }

    private fun initStatusSaveContact(binding: FragmentFormContactBinding) {

        mContactViewModel.contactSavedLiveData.observe(viewLifecycleOwner, Observer {

            binding.resource = it

            if (it.status == Status.SUCCESS) {
                dismissKeyboard(binding.root.windowToken)
                findNavController().navigate(R.id.nav_home)
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentFormContactBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_form_contact,
            container,
            false
        )

        binding.contacts = mContactObservable
        FormContactHelper(binding.root)

        binding.root.findViewById<AppCompatButton>(R.id.btnCancelId).setOnClickListener {
            dismissKeyboard(binding.root.windowToken)
            findNavController().navigate(R.id.nav_home)
        }

        binding.root.findViewById<AppCompatButton>(R.id.btnSaveContactId).setOnClickListener {
            dismissKeyboard(binding.root.windowToken)
            mContactViewModel.setContact(mContactObservable)
        }

        initStatusSaveContact(binding)

        return binding.root
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

}