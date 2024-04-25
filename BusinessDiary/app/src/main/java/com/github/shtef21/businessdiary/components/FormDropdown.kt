package com.github.shtef21.businessdiary.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.github.shtef21.businessdiary.entities.Difficulty
import com.github.shtef21.businessdiary.ui.theme.BlueLog
import com.github.shtef21.businessdiary.ui.theme.GreenLog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormDropdown(
    selectedValue: String,
    options: List<Difficulty>,
    label: String,
    onChange: (Difficulty) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        TextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            label = { Text(text = label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option: Difficulty ->
                DropdownMenuItem(
                    text = {
                        Row {
                            Text(
                                option.toString(),
                                color = option.color,
                                fontWeight = FontWeight.Bold
                            )
                            if (selectedValue == option.toString()) {
                                Text(" (selected)")
                            }
                        }
                    },
                    onClick = {
                        expanded = false
                        onChange(option)
                    }
                )
            }
        }
    }
}