package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment(){
    private var _binding : FragmentLetterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    private fun chooseLayout(){
        if(isLinearLayoutManager){
            recyclerView.layoutManager = LinearLayoutManager(context)
        }else{
            recyclerView.layoutManager = GridLayoutManager(context, 4)
        }
        recyclerView.adapter = LetterAdapter(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
            }
        }
        return true
    }

    private fun setIcon(menuItem : MenuItem?){
        if(menuItem == null){
            return
        }

        if(isLinearLayoutManager){
            menuItem.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_linear_layout)
        }else{
            menuItem.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_grid_layout)
        }
    }
}