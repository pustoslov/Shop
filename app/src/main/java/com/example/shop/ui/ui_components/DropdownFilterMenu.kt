package com.example.shop.ui.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop.R
import com.example.shop.ui.theme.LighterGrey
import com.example.shop.ui.theme.sanFrancisco
import com.example.shop.ui.view_models.CatalogViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownFilterMenu(
    viewModel: CatalogViewModel
) {
    val menuItem = arrayOf("По популярности", "Цена убывание", "Цена возрастание")
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded}
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .menuAnchor()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.two_arrows),
                contentDescription = null
            )
            Text(
                text = viewModel.selectedFilter,
                fontFamily = sanFrancisco,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.arrow_down),
                contentDescription = null
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(LighterGrey)
        ) {
            menuItem.forEach { filterName ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = filterName,
                            fontFamily = sanFrancisco,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    onClick = {
                        expanded = false
                        viewModel.updateFilter(filterName)
                    }
                )
            }
        }

    }
}

@Composable
@Preview
private fun DropMenuPreview() {

}