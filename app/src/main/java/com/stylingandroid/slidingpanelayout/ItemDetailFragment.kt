package com.stylingandroid.slidingpanelayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.stylingandroid.slidingpanelayout.databinding.FragmentItemDetailBinding

class ItemDetailFragment : Fragment(R.layout.fragment_item_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        val binding = FragmentItemDetailBinding.bind(view)
        binding.text.text = args?.getCharSequence(TEXT_ARG)
    }

    companion object {
        const val TEXT_ARG = "Text"
    }
}
