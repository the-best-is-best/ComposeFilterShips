package io.tbib.composefilterchip

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Suppress("defaultValue is not used and will be removed")
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
    state: FilterChipsStates<T>

) {

//    var selectedItems by remember { mutableStateOf(value ?: listOf()) }

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
                            state.selectedItems = if (!state.selectedItems.contains(it)) {
                                (state.selectedItems + it).toMutableList()

                            } else {
                                (state.selectedItems - it).toMutableList()
                            }
                            isSelected(it, state.selectedItems.contains(it))
                        },
                        modifier = modifierContent.padding(horizontal = 10.dp),
                        selected = state.selectedItems.contains(it),
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