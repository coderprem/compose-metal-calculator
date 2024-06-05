package com.example.metalcalculator

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@Composable
fun DetailScreen(modifier: Modifier = Modifier, name: String?, image: Int?) {
    val metalData = getMetalData()
    val shapeSidesData = getShapeSidesData()
    var selectedMetal by remember { mutableStateOf(metalData[0]) }
    var selectedCapsuleButton by remember { mutableStateOf("Length") }
    var pieces by remember { mutableStateOf("") }
    var weightResult by remember { mutableStateOf(0.0) }
    var volumeResult by remember { mutableStateOf(0.0) }
    val sidesNames = shapeSidesData.find { it.shapeName == name }?.sidesName ?: emptyList()
    var sidesValues by remember { mutableStateOf(List(sidesNames.size) { "" }) }
    var selectedUnit_ by remember { mutableStateOf("mm") }
    var selectedMetrics by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (image != null) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = name,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(160.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                MetalDropDown(selectedMetal, onMetalSelected = { selectedMetal = it })
                CapsuleButton(onButtonSelected = { selectedCapsuleButton = it })
                Density(selectedMetal)
            }
        }
        Metrics(sidesNames, sidesValues, onSidesValuesChanged = { newSidesValues, newUnit ->
            sidesValues = newSidesValues
            selectedMetrics = newUnit
        })
        Log.d("DetailScreen", "selectedMetrics: $selectedMetrics")
        if (selectedCapsuleButton == "Length") {
            var selectedUnit by remember { mutableStateOf("mm") }
            selectedUnit_ = selectedUnit
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "${selectedCapsuleButton}:",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
                CapsuleTextField(
                    selectedUnit = selectedUnit,
                    units = listOf("mm", "cm", "m", "in", "ft"),
                    onUnitSelected = { newUnit -> selectedUnit = newUnit }
                )
            }
        } else {
            var selectedUnit by remember { mutableStateOf("g") }
            selectedUnit_ = selectedUnit
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "${selectedCapsuleButton}:",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
                CapsuleTextField(
                    selectedUnit = selectedUnit,
                    units = listOf("g", "kg", "ton"),
                    onUnitSelected = { newUnit -> selectedUnit = newUnit }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = "Pieces:",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            ) {
                OutlinedTextField(
                    value = pieces,
                    onValueChange = {
                        pieces = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .align(Alignment.CenterStart),
                    label = { Text("Value") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                )
            }
        }
        Results(weightResult, volumeResult, selectedCapsuleButton)
        Spacer(modifier = Modifier.weight(1f))
        BottomBar(
            shapeName = name ?: "",
            sidesName = sidesNames,
            sidesValues = sidesValues,
            weightResult = weightResult,
            volumeResult = volumeResult,
            onCalculateClicked = {
            val (weight, volume) = calculate(
                shapeName = name ?: "",
                sidesName = sidesValues,
                selectedMetal = selectedMetal,
                selectedCapsuleButton = selectedCapsuleButton,
                selectedUnit = selectedUnit_,
                selectedMetrics = selectedMetrics,
                pieces = pieces
            )
            weightResult = weight
            volumeResult = volume
        })
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetalDropDown(
    selectedMetal: MetalDetails,
    onMetalSelected: (MetalDetails) -> Unit
) {
    val metalData = getMetalData()

    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = selectedMetal.metalName,
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            modifier = Modifier.menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            metalData.forEachIndexed { _, item ->
                DropdownMenuItem(
                    text = { Text(item.metalName) },
                    onClick = {
                        onMetalSelected(item)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable
fun CapsuleButton(modifier: Modifier = Modifier, onButtonSelected: (String) -> Unit) {
    var selectedButton by remember { mutableStateOf("Length") }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(end = 8.dp, top = 12.dp, bottom = 12.dp)
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "by Length",
            fontSize = 16.sp,
            color = if (selectedButton == "Length") MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .background(
                    if (selectedButton == "Length") MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.primaryContainer
                )
                .padding(horizontal = 10.dp, vertical = 2.dp)
                .clickable {
                    selectedButton = "Length"
                    onButtonSelected("Length")
                }
        )
        Text(
            text = "by Weight",
            fontSize = 16.sp,
            color = if (selectedButton == "Weight") MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier
                .background(
                    if (selectedButton == "Weight") MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.primaryContainer
                )
                .padding(horizontal = 2.dp, vertical = 2.dp)
                .clickable {
                    selectedButton = "Weight"
                    onButtonSelected("Weight")
                }
        )
    }
}


@Composable
fun Density(selectedMetal: MetalDetails) {
    Text(
        text = "Density: ${selectedMetal.metalDensityGramsPerCmCubed} g/cm³",
        fontSize = 16.sp,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
    )
}

@Composable
fun Metrics(
    sidesName: List<String>,
    sidesValues: List<String>,
    onSidesValuesChanged: (List<String>, String) -> Unit,
) {
    // State to keep track of the selected unit
    var selectedUnit by remember { mutableStateOf("mm") }
    val units = listOf("mm", "cm", "m", "in", "ft")

    LazyColumn(
        modifier = Modifier
    ) {
        items(sidesName.size) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "${sidesName[index]} : ",
                    fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
                UnitOutlinedTextField(
                    selectedUnit = selectedUnit,
                    units = units,
                    onUnitSelected = { newUnit -> selectedUnit = newUnit },
                    value = sidesValues[index],
                    onValueChange = { newValue ->
                        val newSidesValues = sidesValues.toMutableList()
                        newSidesValues[index] = newValue
                        onSidesValuesChanged(newSidesValues, selectedUnit)
                    }
                )
                Log.d("DetailScreen", "selected: $selectedUnit")
            }
        }
    }
}

@Composable
fun UnitOutlinedTextField(
    selectedUnit: String,
    units: List<String>,
    onUnitSelected: (String) -> Unit,
    value: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth(0.6f)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.CenterStart),
            label = { Text("Value") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                Box {
                    Text(
                        text = selectedUnit,
                        modifier = Modifier
                            .clickable { expanded = true }
                            .padding(8.dp)
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        units.forEach { unit ->
                            DropdownMenuItem(
                                text = { Text(unit) },
                                onClick = {
                                    onUnitSelected(unit)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun CapsuleTextField(
    selectedUnit: String,
    units: List<String>,
    onUnitSelected: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth(0.6f)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.CenterStart),
            label = { Text("Value") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            trailingIcon = {
                Box {
                    Text(
                        text = selectedUnit,
                        modifier = Modifier
                            .clickable { expanded = true }
                            .padding(8.dp)
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        units.forEach { unit ->
                            DropdownMenuItem(
                                text = { Text(unit) },
                                onClick = {
                                    onUnitSelected(unit)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun Results(
    weight: Double,
    volume: Double,
    selectedCapsuleButton: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
    ) {
        Text(
            text = "Results",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (selectedCapsuleButton == "Length") "Weight: " else "Length: ",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
            Text(
                text = "$weight ${if (selectedCapsuleButton == "Length") "kg" else "m"}",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Volume: ",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
            Text(
                text = "$volume cm³",
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
        }
    }
}

@Composable
fun BottomBar(
    shapeName: String,
    sidesName: List<String>,
    sidesValues: List<String>,
    weightResult: Double,
    volumeResult: Double,
    onCalculateClicked: () -> Unit
) {
    val content = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    // Get the current date and time
    val currentDateTime = remember {
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

    // Combine sides names and values
    val sidesText = sidesName.zip(sidesValues) { name, value -> "$name - $value" }.joinToString(", ")

    // Create the text to copy
    val textToCopy = "$shapeName - $currentDateTime\n\n$sidesText\nWeight: $weightResult kg\nVolume: $volumeResult cm³"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = {
            shareText(content, textToCopy)
        }) {
            Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
        }
        Button(onClick = { onCalculateClicked() }) {
            Text(text = "Calculate")
        }
        IconButton(onClick = {
            clipboardManager.setText(AnnotatedString(text = textToCopy))
        }) {
            Icon(
                painter = painterResource(id = R.drawable.copy_48), contentDescription = "Copy",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

private fun shareText(context: Context, text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}

fun calculate(
    shapeName: String,
    sidesName: List<String>,
    selectedMetal: MetalDetails,
    selectedCapsuleButton: String,
    selectedUnit: String,
    pieces: String,
    selectedMetrics: String
): Pair<Double, Double> {
    val density = selectedMetal.metalDensityGramsPerCmCubed

    if (selectedCapsuleButton == "Length") {
        val length = sidesName.last().toDoubleOrNull() ?: 0.0
        val piecesCount = pieces.toDoubleOrNull() ?: 0.0

        // Convert length to cm based on unit
        val lengthInCm = when (selectedUnit) {
            "mm" -> length / 10.0
            "cm" -> length
            "m" -> length * 100.0
            "in" -> length * 2.54
            "ft" -> length * 30.48
            else -> length
        }

        if (shapeName == "Hexagon") {
            return hexagon(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else if (shapeName == "Round Bar") {
            return round_bar(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else if (shapeName == "Round Tube") {
            return round_tube(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else if (shapeName == "Square Bar") {
            return square_bar(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else if (shapeName == "Square Tube") {
            return square_tube(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else if (shapeName == "T Bar") {
            return t_bar(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else if (shapeName == "Beams") {
            return beams(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else if (shapeName == "Channel") {
            return channel(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else if (shapeName == "Angle") {
            return angle(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else if (shapeName == "Flat Bar") {
            return flat_bar(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        } else {
            return sheet(
                sidesName,
                density,
                lengthInCm / 100.0,
                selectedCapsuleButton,
                selectedMetrics,
                piecesCount
            )
        }
    } else {
        var weightInput = sidesName.last().toDoubleOrNull() ?: 0.0
        val piecesWeight = pieces.toDoubleOrNull() ?: 0.0

        // Convert weight to grams based on unit
        weightInput = when (selectedUnit) {
            "g" -> weightInput
            "kg" -> weightInput * 1000.0
            "ton" -> weightInput * 1000000.0
            else -> weightInput
        }
        return hexagon(
            sidesName,
            density,
            weightInput,
            selectedCapsuleButton,
            selectedMetrics,
            piecesWeight
        )
    }

}


