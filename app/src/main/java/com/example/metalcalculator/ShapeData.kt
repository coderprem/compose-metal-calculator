package com.example.metalcalculator

data class ShapeData(
    val shapeName: String,
    val shapeImage: Images,
)

data class Images(
    val outerImage: Int,
    val innerImage: Int,
)

data class Sides(
    val shapeName: String,
    val sidesName: List<String>
)

fun getShapeData(): List<ShapeData> {
    return listOf(
        ShapeData("Hexagon", Images(R.drawable.hexagon, R.drawable.hexagon_detail)),
        ShapeData("Round Bar", Images(R.drawable.round_bar, R.drawable.round_bar_detail)),
        ShapeData("Round Tube", Images(R.drawable.round_tube, R.drawable.round_tube_detail)),
        ShapeData("Square Bar", Images(R.drawable.square_bar, R.drawable.square_bar_detail)),
        ShapeData("Square Tube", Images(R.drawable.square_tube, R.drawable.square_tube_detail)),
        ShapeData("T Bar", Images(R.drawable.t_bar, R.drawable.t_bar_detail)),
        ShapeData("Beams", Images(R.drawable.beams, R.drawable.beams_detail)),
        ShapeData("Channel", Images(R.drawable.channel, R.drawable.channel_detail)),
        ShapeData("Angle", Images(R.drawable.angle, R.drawable.angle_detail)),
        ShapeData("Flat Bar", Images(R.drawable.flat_bar, R.drawable.flat_bar_detail)),
        ShapeData("Sheet", Images(R.drawable.sheet, R.drawable.sheet_detail)),
    )
}

fun getShapeSidesData(): List<Sides> {
    return listOf(
        Sides(
            shapeName = "Hexagon",
            sidesName = listOf("Width (A)"),
        ),
        Sides(
            shapeName = "Round Bar",
            sidesName = listOf("Diameter (D)"),
        ),
        Sides(
            shapeName = "Round Tube",
            sidesName = listOf("Diameter (D)", "Thickness (T)"),
        ),
        Sides(
            shapeName = "Square Bar",
            sidesName = listOf("Side (A)"),
        ),
        Sides(
            shapeName = "Square Tube",
            sidesName = listOf("Side (A)", "Side (B)", "Thickness (T)"),
        ),
        Sides(
            shapeName = "T Bar",
            sidesName = listOf("Side (A)", "Side (B)", "Thickness (T)"),
        ),
        Sides(
            shapeName = "Beams",
            sidesName = listOf("Side (A)", "Side (B)", "Thickness (T)", "Thickness (T)"),
        ),
        Sides(
            shapeName = "Channel",
            sidesName = listOf("Side (A)", "Side (B)", "Thickness (T)", "Thickness (T)"),
        ),
        Sides(
            shapeName = "Angle",
            sidesName = listOf("Side (A)", "Side (B)", "Thickness (T)"),
        ),
        Sides(
            shapeName = "Flat Bar",
            sidesName = listOf("Side (A)", "Side (B)"),
        ),
        Sides(
            shapeName = "Sheet",
            sidesName = listOf("Side (A)", "Side (B)"),
        ),
    )
}