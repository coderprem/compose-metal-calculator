package com.example.metalcalculator

import java.util.Locale
import kotlin.math.pow
import kotlin.math.sqrt

fun hexagon(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    var width = sidesName[0].toDoubleOrNull() ?: 0.0


    width /= sqrt(3.0)
    val area = 3 * sqrt(3.0) * width.pow(2.0) / 2

    volume = area * lengthOrWeight * pieces
    weight = volume * density * pieces
    weight /= 1000

    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when height is unknown
        var length = weight / density
        length /= area
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun round_bar(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    var diameter = sidesName[0].toDoubleOrNull() ?: 0.0

    val radius = diameter / 2
    val area = Math.PI * radius.pow(2)

    volume = area * lengthOrWeight * pieces
    weight = volume * density * pieces
    weight /= 1000

    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when height is unknown
        var length = weight / density
        length /= area
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun round_tube(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    var diameter = sidesName[0].toDoubleOrNull() ?: 0.0
    var thickness = sidesName[1].toDoubleOrNull() ?: 0.0

    val outerRadius = diameter / 2
    val innerRadius = outerRadius - thickness
    val outerArea = Math.PI * outerRadius.pow(2)
    val innerArea = Math.PI * innerRadius.pow(2)

    volume = (outerArea - innerArea) * lengthOrWeight * pieces
    weight = volume * density * pieces
    weight /= 100

    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when height is unknown
        var length = weight / density
        length /= (outerArea - innerArea)
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun square_bar(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    var side = sidesName[0].toDoubleOrNull() ?: 0.0

    val area = side.pow(2)

    volume = area * lengthOrWeight * pieces
    weight = volume * density * pieces
    weight /= 10

    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when height is unknown
        var length = weight / density
        length /= area
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun square_tube(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    var sideA = sidesName[0].toDoubleOrNull() ?: 0.0
    var sideB = sidesName[1].toDoubleOrNull() ?: 0.0
    var thickness = sidesName[2].toDoubleOrNull() ?: 0.0

    val outerArea = sideA * sideB
    val innerArea = (sideA - 2 * thickness) * (sideB - 2 * thickness)

    volume = (outerArea - innerArea) * lengthOrWeight * pieces
    weight = volume * density * pieces
    weight /= 10

    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when height is unknown
        var length = weight / density
        length /= (outerArea - innerArea)
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun t_bar(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    var sideA = sidesName[0].toDoubleOrNull() ?: 0.0
    var sideB = sidesName[1].toDoubleOrNull() ?: 0.0
    var thickness = sidesName[2].toDoubleOrNull() ?: 0.0

    // Calculate the volume of the T-shaped bar
    volume = lengthOrWeight * thickness * (sideA + sideB - thickness) * pieces

    // Calculate the weight of the T-shaped bar
    weight = volume * density * pieces
    weight /= 10 // Convert to kg

    // Adjust weight and volume according to the selected metric
    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when weight is given
        var length = weight / density
        length /= thickness * (sideA + sideB - thickness)
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun beams(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    val sideA = sidesName[0].toDoubleOrNull() ?: 0.0
    val sideB = sidesName[1].toDoubleOrNull() ?: 0.0
    val thicknessT = sidesName[2].toDoubleOrNull() ?: 0.0
    val thicknessS = sidesName[3].toDoubleOrNull() ?: 0.0

    // Calculate the volume of the H-shaped bar
    val verticalVolume = 2 * sideA * thicknessS * lengthOrWeight
    val horizontalVolume = sideB * thicknessT * lengthOrWeight
    val extraVolume = 2 * thicknessS * thicknessS * lengthOrWeight

    volume = (verticalVolume + horizontalVolume - extraVolume) * pieces

    // Calculate the weight of the H-shaped bar
    weight = volume * density * pieces
    weight /= 10 // Convert to kg

    // Adjust weight and volume according to the selected metric
    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when weight is given
        var length = weight / density
        length /= (2 * sideA * thicknessS + sideB * thicknessT + 2 * thicknessS * thicknessS)
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun channel(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    val sideA = sidesName[0].toDoubleOrNull() ?: 0.0
    val sideB = sidesName[1].toDoubleOrNull() ?: 0.0
    val thicknessT = sidesName[2].toDoubleOrNull() ?: 0.0
    val thicknessS = sidesName[3].toDoubleOrNull() ?: 0.0

    // Calculate the volume of the C-shaped bar
    val verticalVolume = sideA * thicknessS * lengthOrWeight
    val horizontalVolume = 2 * sideB * thicknessT * lengthOrWeight
    val extraVolume = 2 * thicknessS * thicknessT * lengthOrWeight

    volume = (verticalVolume + horizontalVolume - extraVolume) * pieces

    // Calculate the weight of the C-shaped bar
    weight = volume * density * pieces
    weight /= 10 // Convert to kg

    // Adjust weight and volume according to the selected metric
    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when weight is given
        var length = weight / density
        length /= (sideA * thicknessS + sideB * thicknessT + 2 * thicknessS * thicknessT)
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun angle(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    var sideA = sidesName[0].toDoubleOrNull() ?: 0.0
    var sideB = sidesName[1].toDoubleOrNull() ?: 0.0
    var thickness = sidesName[2].toDoubleOrNull() ?: 0.0

    // Calculate the volume of the T-shaped bar
    volume = lengthOrWeight * thickness * (sideA + sideB - thickness) * pieces

    // Calculate the weight of the T-shaped bar
    weight = volume * density * pieces
    weight /= 10 // Convert to kg

    // Adjust weight and volume according to the selected metric
    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when weight is given
        var length = weight / density
        length /= thickness * (sideA + sideB - thickness)
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun flat_bar(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    var sideA = sidesName[0].toDoubleOrNull() ?: 0.0
    var sideB = sidesName[1].toDoubleOrNull() ?: 0.0

    // Calculate the volume of the flat bar
    volume = sideA * sideB * lengthOrWeight * pieces

    // Calculate the weight of the flat bar
    weight = volume * density * pieces
    weight /= 10 // Convert to kg

    // Adjust weight and volume according to the selected metric
    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when weight is given
        var length = weight / density
        length /= sideA * sideB
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}

fun sheet(
    sidesName: List<String>,
    density: Double,
    lengthOrWeight: Double,
    selectedCapsuleButton: String,
    selectedMetricsInCm: String,
    pieces: Double
): Pair<Double, Double> {
    var weight = 0.0
    var volume = 0.0

    var sideA = sidesName[0].toDoubleOrNull() ?: 0.0
    var sideB = sidesName[1].toDoubleOrNull() ?: 0.0

    // Calculate the volume of the flat bar
    volume = sideA * sideB * lengthOrWeight * pieces

    // Calculate the weight of the flat bar
    weight = volume * density * pieces
    weight /= 1000 // Convert to kg

    // Adjust weight and volume according to the selected metric
    when (selectedMetricsInCm) {
        "mm" -> {
            weight /= 100
            volume /= 100
        }

        "cm" -> {
            weight /= 1000
            volume /= 1000
        }

        "m" -> {
            weight /= 1000000
            volume /= 1000000
        }

        "in" -> {
            weight /= 1000
            volume /= 1000
        }

        "ft" -> {
            weight /= 1000
            volume /= 1000
        }
    }

    weight = String.format(Locale.getDefault(), "%.2f", weight).toDouble()
    volume = String.format(Locale.getDefault(), "%.2f", volume).toDouble()

    if (selectedCapsuleButton == "Length") {
        return Pair(weight, volume)
    } else {
        // Use the volume formula to calculate length when weight is given
        var length = weight / density
        length /= sideA * sideB
        length = String.format(Locale.getDefault(), "%.2f", length).toDouble()
        return Pair(length, volume)
    }
}
