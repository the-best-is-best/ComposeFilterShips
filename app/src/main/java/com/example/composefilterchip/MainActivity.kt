package com.example.composefilterchip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composefilterchip.ui.theme.ComposeFilterChipTheme
import io.tbib.composefilterchip.FilterChipsMultiSelector
import io.tbib.composefilterchip.rememberFilterChipsStates

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
    val selectedItemsState = rememberFilterChipsStates( value = data.take(5))

    FilterChipsMultiSelector(
        modifier = modifier,
        listOfItems = data,
        state = selectedItemsState ,

        isSelected = { _, _ ->

            println("count is ${selectedItemsState.selectedItems.size} and selected items are ${selectedItemsState.selectedItems}")
        },
        textDisplay = {
            Text("Item ${it.id} ${it.name}")
        }
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeFilterChipTheme {
        Greeting("Android")
    }
}