package com.example.composemvvmdemo.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composemvvmdemo.viewmodel.BenefitViewModel

@Composable
fun BenefitScreen() {
    val benefitViewModel: BenefitViewModel = hiltViewModel()
    val benefits = benefitViewModel.benefit.collectAsState()

    LazyColumn() {
        items(benefits.value) {
            BenefitItem(benefit = it.benefit)
        }
    }
}

@Composable
fun BenefitItem(benefit: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        border = BorderStroke(1.dp, Color(0XFFEEEEEE))
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.body2,
            text = benefit
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewScreen() {
    BenefitItem(benefit = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam vestibulum risus sit amet ex suscipit pulvinar.")
}