package com.example.jetpackexpirience.presentation

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.jetpackexpirience.R
import com.example.jetpackexpirience.databinding.FragmentGameFinishedBinding
import com.example.jetpackexpirience.databinding.FragmentWelcomeBinding
import com.example.jetpackexpirience.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private val gameResult: GameResult by lazy{
        val args = GameFinishedFragmentArgs.fromBundle(requireArguments())
        args.gameResult
    }


    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        setUpUI()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpUI() {
        with(binding){
            emojiResult.setImageResource(getSmileResId())
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfRightAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameResult.gameSettings.minPercentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers()
            )
        }

    }

    private fun getSmileResId(): Int{
        return if (gameResult.winner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }
    }
    private fun getPercentOfRightAnswers(): Int = with(gameResult){
        if(countOfQuestions == 0){
            0
        } else{
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }

    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}