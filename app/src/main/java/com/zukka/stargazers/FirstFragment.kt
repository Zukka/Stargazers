package com.zukka.stargazers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zukka.stargazers.databinding.FragmentFirstBinding
import com.zukka.stargazers.enums.GitHubValidatorResult
import com.zukka.stargazers.network.StargazerPropertyList
import com.zukka.stargazers.utils.ErrorDialog
import com.zukka.stargazers.viewmodels.FirstFragmentViewModel


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var viewModel = FirstFragmentViewModel()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel.connectionChecker.observe(viewLifecycleOwner) {
            if (!it)
                showConnectionErrorDialog()
        }

        viewModel.stargazers.observe(viewLifecycleOwner) {
            when {
                it == null -> return@observe
                it.isNotEmpty() -> {
                    val action = FirstFragmentDirections.actionFirstFragmentToStargazersFragment(
                        StargazerPropertyList(it))
                    findNavController().navigate(action)
                }
                else -> {
                    val errorDialog = ErrorDialog(getString(R.string.no_stargazers))
                    errorDialog.show(parentFragmentManager,"NO_STARGAZERS")
                }
            }
        }

        setSearchButtonListener()
        return binding.root
    }

    private fun showConnectionErrorDialog() {
        val errorDialog = ErrorDialog(getString(R.string.connection_error))
        errorDialog.show(parentFragmentManager, "CONNECTION_ERROR")
    }

    private fun setSearchButtonListener() {
        _binding!!.search.setOnClickListener {
            if (!connectionStatus())
                return@setOnClickListener

            validateData()
        }
    }

    private fun validateData() {
        val username = _binding!!.githubUsername.text.toString()
        val repositoryName = _binding!!.githubRepository.text.toString()
        val gitHubValidatorResult = viewModel.validateData(username, repositoryName)
        when {
            gitHubValidatorResult != GitHubValidatorResult.Success -> {
                val errorDialog = ErrorDialog(gitHubValidatorResult.value)
                errorDialog.show(parentFragmentManager, "GITHUB_VALIDATOR_ERROR")
            }
            else -> viewModel.getStargazers(username, repositoryName)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startStarAnimation()
        startMoonAnimation()
    }

    private fun connectionStatus(): Boolean{
        return viewModel.checkConnectivity(requireContext())
    }

    private fun startMoonAnimation() {
        _binding!!.moon.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.infinite_rotate))
    }

    private fun startStarAnimation() {
        _binding!!.starOne.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.infinite_fadein_fadeout
            )
        )
        _binding!!.starTwo.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.infinite_fadein_fadeout_two
            )
        )
        _binding!!.starThree.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.infinite_fadein_fadeout_three
            )
        )
        _binding!!.starFour.startAnimation(
            AnimationUtils.loadAnimation(
                requireContext(),
                R.anim.infinite_fadein_fadeout_four
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.resetLiveData()
        _binding = null
    }
}