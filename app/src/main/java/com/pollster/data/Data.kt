package com.pollster.data

import androidx.compose.runtime.Composable

data class ImageCard(
    val title: String,
    val description: String,
    val onClickCard: @Composable () -> Unit)