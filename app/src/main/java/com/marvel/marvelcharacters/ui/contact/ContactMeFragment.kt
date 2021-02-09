package com.marvel.marvelcharacters.ui.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marvel.marvelcharacters.R
import com.marvel.marvelcharacters.databinding.FragmentContactMeBinding

class ContactMeFragment : Fragment() {

    private lateinit var binding: FragmentContactMeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentContactMeBinding.bind(view)
        binding.email.emailCard.setOnClickListener {
            sendMail()
        }
    }

    private fun sendMail(){
        val emailIntent = Intent(
            Intent.ACTION_SENDTO
        )
        val uriText = "mailto:" + Uri.encode(requireContext().getString(R.string.email))
        val uri = Uri.parse(uriText)
        emailIntent.data = uri
        startActivity(Intent.createChooser(emailIntent, requireContext().getString(R.string.send_mail)))
    }

}