package io.tbib.composefilterchip

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


@Composable
fun<T> rememberFilterChipsStates( value: List<T>? = null): FilterChipsStates<T> {
    return rememberSaveable(saver = FilterChipsStates.Saver()  ) {
        FilterChipsStates(value)
    }
}

@SuppressLint("MutableCollectionMutableState")
class FilterChipsStates<T> internal constructor(
    value: List<T>?
) {
    var selectedItems by mutableStateOf(listOf<T>())


    init {
        selectedItems = value?.toMutableList()?: selectedItems
    }

    fun clear(defaultValue:List<T>? = null) {
        selectedItems = defaultValue ?: listOf()

    }



    companion object {
        fun <T> Saver(): Saver<FilterChipsStates<T>, *> = listSaver(
            save = {


                  listOf(
                      it.selectedItems,
                  )

            },
            restore = {
                val data = FilterChipsStates<T>(null)
                data.selectedItems = it[0]
                data
            })
    }

}
