package com.example.composefilterchip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composefilterchip.ui.theme.ComposeFilterChipTheme
import io.tbib.composefilterchip.FilterChipsMultiSelector

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFilterChipTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    data class ExampleData(
        val id: Int,
        val name: String
    )
    val data = List(1000) { index ->
        ExampleData(index + 1, "Item ${index + 1}")
    }
    var selectedItems by remember { mutableStateOf(listOf<ExampleData>()) }
    FilterChipsMultiSelector(
        modifier = modifier,
        listOfItems = data,
        isSelected = { item, isSelected ->

            selectedItems = if (isSelected) {
                selectedItems + item
            } else {
                selectedItems - item
            }
            println("count is ${selectedItems.size} and selected items are $selectedItems")
        },
        textDisplay = { it.name }
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeFilterChipTheme {
        Greeting("Android")
    }
}