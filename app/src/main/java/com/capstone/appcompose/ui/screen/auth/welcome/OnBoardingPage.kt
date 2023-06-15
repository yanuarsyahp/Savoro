package com.capstone.appcompose.ui.screen.auth.welcome

import androidx.annotation.DrawableRes
import com.capstone.appcompose.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.diet1,
        title = "Small Steps to Your \nHealth and Happiness",
        description = "Every small step you take in your \ndiet journey brings you closer to \nthe health and happiness you \nwant."
    )
    object Second : OnBoardingPage(
        image = R.drawable.diet2,
        title = "A Pleasant Diet\n",
        description = "Make your dietary changes an \nexciting and enjoyable journey :D \n \n"
    )
    object Third : OnBoardingPage(
        image = R.drawable.diet3,
        title = "Start Your Healthy Journey\n",
        description = "We understand that starting a diet \nis not easy, but we can’\t be what \nwe want, if every day only do \nwhat we want!"
    )
}