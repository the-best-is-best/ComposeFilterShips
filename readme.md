<h1 align="center">Compose Filter Chips</h1><br>

<div align="center">
<a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
<a href="https://android-arsenal.com/api?level=21" rel="nofollow"><img alt="API" src="https://camo.githubusercontent.com/0eda703da08220e08354f624a3fc0023f10416a302565c69c3759bf6e0800d40/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4150492d32312532422d627269676874677265656e2e7376673f7374796c653d666c6174" data-canonical-src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" style="max-width: 100%;"></a>
<a href="https://github.com/the-best-is-best/"><img alt="Profile" src="https://img.shields.io/badge/github-%23181717.svg?&style=for-the-badge&logo=github&logoColor=white" height="20"/></a>
<a href="https://central.sonatype.com/search?q=io.github.the-best-is-best&smo=true"><img alt="Maven Central" src="https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose_filter_chips"/></a>
</div>

Compose Filter Chips is a Jetpack Compose library for Android that provides a filter chips.

## Download

[![Maven Central](https://img.shields.io/maven-central/v/io.github.the-best-is-best/compose_filter_chips)](https://central.sonatype.com/artifact/io.github.the-best-is-best/compose_filter_chips)

Compose Filter Chips is available on `mavenCentral()`.

```kotlin
implementation("io.github.the-best-is-best:compose_filter_chips:1.0.0")
```

## How to use

```kotlin
data class ExampleData(
        val id: Int,
        val name: String
    )
    val data = List(100) { index ->
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
```