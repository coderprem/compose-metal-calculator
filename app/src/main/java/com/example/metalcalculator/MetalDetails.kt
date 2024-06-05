package com.example.metalcalculator

data class MetalDetails(
    val metalName: String,
    val metalDensityGramsPerCmCubed: Double,
)

fun getMetalData(): List<MetalDetails> {
    return listOf(
        MetalDetails("Steel", 7.85),
        MetalDetails("Aluminium", 2.73),
        MetalDetails("Brass", 8.47),
        MetalDetails("Copper", 8.96),
        MetalDetails("Bronze", 8.8),
        MetalDetails("Zinc", 7.14),
        MetalDetails("Chromium", 7.55),
        MetalDetails("Lead", 11.34),
        MetalDetails("Iron", 7.87),
        MetalDetails("Gold", 19.32),
        MetalDetails("Magnesium", 1.74),
        MetalDetails("Nickel", 8.91),
        MetalDetails("Titanium", 4.5),
        MetalDetails("Tin", 7.3),
        MetalDetails("Teflon", 2.2),
        MetalDetails("Silver", 10.49),
        MetalDetails("Platinum", 21.45),
        MetalDetails("SS 304/310", 7.92),
        MetalDetails("SS 316/321", 7.94),
        MetalDetails("SS 410/430", 7.71),
        MetalDetails("Zirconium", 6.52),
        MetalDetails("Molybdenum", 10.28),
    )
}