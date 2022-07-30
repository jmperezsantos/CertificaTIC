package org.certificatic.phoneagendacomposeexample.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import org.certificatic.phoneagendacomposeexample.R

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.tucan))

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}