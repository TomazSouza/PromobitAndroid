package com.seletivo.promobit.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seletivo.promobit.R
import com.seletivo.promobit.databinding.FragmentFormContactBinding

class ContactFormFragment : Fragment() {

//    private lateinit var galleryViewModel: GalleryViewModel

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