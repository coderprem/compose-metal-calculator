package com.example.metalcalculator

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ListScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TopBar(modifier = modifier)
        val shapeData = getShapeData()
        LazyColumn {
            items(shapeData.size) { index ->
                ListItem(
                    name = shapeData[index].shapeName,
                    image = shapeData[index].shapeImage,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    name: String,
    image: Images,
    navController: NavHostController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(bottom = 8.dp)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                navController.navigate("detail?name=$name&image=${image.innerImage}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = image.outerImage),
            contentDescription = name,
            modifier = Modifier
                .size(64.dp)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = name,
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp
        )
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "Metal Calculator",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .padding(start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.baseline_folder_special_24),
            contentDescription = "Favorite",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .size(32.dp)
                .clickable {
                    Toast.makeText(context, "Saved calculations will be saved here", Toast.LENGTH_SHORT).show()
                }
        )
    }
}