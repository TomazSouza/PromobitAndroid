package com.seletivo.promobit.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.seletivo.promobit.R
import com.seletivo.promobit.databinding.FragmentFormContactBinding
import com.seletivo.promobit.di.annotation.Injectable
import com.seletivo.promobit.util.async.AppExecutors
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

//        galleryViewModel =
//            ViewModelProviders.of(this).get(GalleryViewModel::class.java)


        val binding: FragmentFormContactBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_form_contact,
            container,
            false
        )

        return binding.root
    }

}