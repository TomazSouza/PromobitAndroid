package com.seletivo.promobit.ui.form

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.seletivo.promobit.R
import com.seletivo.promobit.databinding.FragmentFormBinding
import com.seletivo.promobit.di.annotation.Injectable
import com.seletivo.promobit.enums.Status
import javax.inject.Inject


class FormFragment : Fragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var contactObservable: ContactObservable

    private lateinit var navController: NavController

    private val formViewModel: FormViewModel by viewModels { mViewModelFactory }

    private fun initStatusSaveContact(binding: FragmentFormBinding) {
        formViewModel.contactSavedLiveData.observe(viewLifecycleOwner, Observer {

            binding.resource = it

            if (it.status == Status.SUCCESS) {
                dismissKeyboard(binding.root.windowToken)
                onBackPressed()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentFormBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_form,
            container,
            false
        )

        navController = findNavController()

        binding.contacts = contactObservable

        FormHelper(binding.root)

        binding.root.findViewById<AppCompatButton>(R.id.btnCancelId).setOnClickListener {
            dismissKeyboard(binding.root.windowToken)
            onBackPressed()
        }

        binding.root.findViewById<AppCompatButton>(R.id.btnSaveContactId).setOnClickListener {
            dismissKeyboard(binding.root.windowToken)
            formViewModel.setContact(contactObservable)
        }

        initStatusSaveContact(binding)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            onBackPressed()
        }

        return binding.root
    }

    private fun onBackPressed() {
        navController.popBackStack(R.id.nav_home, false)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

}