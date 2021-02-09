package com.marvel.marvelcharacters.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.marvel.marvelcharacters.R

class LoaderDialogFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_loader, container, false)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).also { dialog ->
            dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        }

    companion object {
        fun newInstance(): LoaderDialogFragment {
            val dialog = LoaderDialogFragment()
            dialog.isCancelable = false
            return dialog
        }
    }

}