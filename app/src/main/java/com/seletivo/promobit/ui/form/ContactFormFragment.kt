package com.seletivo.promobit.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.seletivo.promobit.R
import com.seletivo.promobit.databinding.FragmentFormContactBinding
import com.seletivo.promobit.di.annotation.Injectable
import com.seletivo.promobit.util.async.AppExecutors
import kotlinx.android.synthetic.main.fragment_form_contact.*
import kotlinx.android.synthetic.main.fragment_form_contact.view.*
import javax.inject.Inject

class ContactFormFragment : Fragment(), Injectable {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private val mContactViewModel: ContactViewModel by viewModels { mViewModelFactory }

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

        val cancelar = binding.root.findViewById<AppCompatButton>(R.id.btnCancelId)
        val salvar = binding.root.findViewById<AppCompatButton>(R.id.btnSaveContactId)

        cancelar.setOnClickListener {
            findNavController().navigate(R.id.nav_home)
        }
        salvar.setOnClickListener {
            findNavController().navigate(R.id.nav_home)
        }
        return binding.root
    }

}