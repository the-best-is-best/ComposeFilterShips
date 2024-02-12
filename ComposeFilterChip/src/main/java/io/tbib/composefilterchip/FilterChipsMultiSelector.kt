package io.tbib.composefilterchip

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.SelectableChipElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("RememberReturnType")
@Composable
fun<T> FilterChipsMultiSelector(
    modifier: Modifier = Modifier,
    modifierContent: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    maxItemsInEachRow: Int = Int.MAX_VALUE,
    listOfItems: List<T>,
    listOfItemsEnabled: List<Boolean> = listOfItems.map { true },
    colors: SelectableChipColors = FilterChipDefaults.elevatedFilterChipColors(),
    shape: Shape = FilterChipDefaults.shape,
    isSelected: (T, Boolean) -> Unit,
    leadingIcon: @Composable (() -> Unit) = {  },
    trailingIcon: @Composable (() -> Unit) = {  },
    elevation: SelectableChipElevation = FilterChipDefaults.elevatedFilterChipElevation(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    textDisplay: @Composable ( (T) -> Unit),
    defaultValue:List<T>? = null

) {

    var selectedItems by rememberSaveable { mutableStateOf(listOf<T>()) }
    var init by rememberSaveable { mutableStateOf(false) }
    if (!init) {
        selectedItems = defaultValue ?: listOf()
        init = true
    }
    LazyColumn {

        item {
            FlowRow(
                modifier = modifier,
                horizontalArrangement = horizontalArrangement,
                verticalArrangement = verticalArrangement,
                maxItemsInEachRow = maxItemsInEachRow
            ) {
                listOfItems.forEach {
                    ElevatedFilterChip(
                        enabled = listOfItemsEnabled[listOfItems.indexOf(it)],
                        label = { textDisplay(it) },
                        onClick = {
                            selectedItems = if (!selectedItems.contains(it)) {
                                selectedItems + it
                            } else {
                                selectedItems - it
                            }
                            isSelected(it, selectedItems.contains(it))
                        },
                        modifier = modifierContent.padding(horizontal = 10.dp),
                        selected = selectedItems.contains(it),
                        colors = colors,
                        shape = shape,
                        leadingIcon = leadingIcon,
                        trailingIcon = trailingIcon,
                        elevation = elevation,
                        border = border,
                        interactionSource = interactionSource

                    )
                }
            }
        }
    }

}