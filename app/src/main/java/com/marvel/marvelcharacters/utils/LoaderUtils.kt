package com.marvel.marvelcharacters.utils

import androidx.fragment.app.Fragment
import com.marvel.marvelcharacters.MainActivity

fun showLoader(toShow: Boolean, fragment: Fragment){
    if (toShow){
        (fragment.activity as MainActivity).showLoader()
    }else{
        (fragment.activity as MainActivity).hideLoader()
    }
}