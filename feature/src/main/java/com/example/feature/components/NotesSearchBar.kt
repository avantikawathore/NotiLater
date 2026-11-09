package com.example.feature.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotesSearchBar(
    value: String,
    onValueChange : (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange =  onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text( text = "Search your notes...") },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            Icon(Icons.Default.Tune, contentDescription = "Filters")
        },
        singleLine = true,
        shape = RoundedCornerShape(18.dp)
    )
}