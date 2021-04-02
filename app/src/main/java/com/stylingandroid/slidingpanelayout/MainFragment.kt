package com.stylingandroid.slidingpanelayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stylingandroid.slidingpanelayout.databinding.ListItemBinding
import com.stylingandroid.slidingpanelayout.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.main_fragment) {

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = MainFragmentBinding.bind(view)
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = TextItemAdapter(viewModel.listItems) { item ->
                navController.navigate(
                    R.id.itemDetailFragment,
                    bundleOf(ItemDetailFragment.TEXT_ARG to item)
                )
                binding.root.open()
            }
        }
    }

    private class TextItemAdapter(
        private val items: List<String>,
        private val selector: (String) -> Unit
    ) :
        RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
                .let { view ->
                    ViewHolder(
                        ListItemBinding.bind(view)
                    )
                }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.bind(item) {
                selector(item)
            }
        }

        override fun getItemCount(): Int = items.size
    }

    private class ViewHolder(val itemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(text: CharSequence, onClickListener: View.OnClickListener) {
            itemBinding.text.text = text
            itemBinding.text.setOnClickListener(onClickListener)
        }
    }
}
