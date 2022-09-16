package com.zukka.stargazers.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.zukka.stargazers.databinding.DialogErrorBinding

class ErrorDialog(private val errorMessage: String): DialogFragment() {

    private lateinit var binding: DialogErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DialogErrorBinding.inflate(inflater, container, false)
        binding.textError.text = errorMessage
        binding.closeDialogConnectionError.setOnClickListener {
            dialog!!.dismiss()
        }

        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        return binding.root
    }
}