package com.zukka.stargazers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.zukka.stargazers.databinding.FragmentStargazersBinding

class StargazersFragment : Fragment() {

    private val args: StargazersFragmentArgs by navArgs()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: StargazerListAdapter

    private var _binding: FragmentStargazersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStargazersBinding.inflate(inflater, container, false)

        linearLayoutManager = LinearLayoutManager(requireContext())
        _binding!!.recyclerView.layoutManager = linearLayoutManager
        adapter = StargazerListAdapter(args.stargazers.stargazersList)
        _binding!!.recyclerView.adapter = adapter
        return binding.root
    }

}