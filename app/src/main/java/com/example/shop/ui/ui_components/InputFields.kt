package com.example.shop.ui.ui_components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shop.ui.theme.DarkGrey
import com.example.shop.ui.theme.ErrorRed
import com.example.shop.ui.theme.Grey
import com.example.shop.ui.theme.LighterGrey
import com.example.shop.ui.theme.sanFrancisco

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    isValid: Boolean,
    text: String,
    placeholder: String,
    onClear: () -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val color = if (isValid) LighterGrey else ErrorRed

    TextField(
        value = text,
        onValueChange = { value -> onValueChange(value) },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = DarkGrey,
            containerColor = color,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .height(50.dp)
            .width(343.dp),
        placeholder = {
            Text(
                text = placeholder,
                color = Grey,
                fontFamily = sanFrancisco
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "",
                    tint = DarkGrey,
                    modifier = Modifier
                        .clickable {
                            onClear()
                        }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberField(
    number: String,
    placeholder: String,
    onClear: () -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isInFocus = interactionSource.collectIsFocusedAsState()

    fun numberTransformer(): TransformedText {
        val mask = "XXX-XXX-XX-XX"

        val annotatedString = AnnotatedString.Builder().run {
            append("+7 ")
            for (i in number.indices) {
                append(number[i])
                if (i == 2 || i == 5 || i == 7) append(" ")
            }
            pushStyle(SpanStyle(color = Grey))
            append(mask.takeLast(mask.length - length + 3))
            toAnnotatedString()
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 2) return offset + 3
                if (offset <= 5) return offset + 4
                if (offset <= 7) return offset + 5
                if (offset <= 9) return offset + 6
                return 16
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return 0
                if (offset <= 6) {
                    if (offset > number.length + 3) return number.length
                    return offset - 3
                }
                if (offset <= 10) {
                    if (offset > number.length + 4) return number.length
                    return offset - 4
                }
                if (offset <= 13) {
                    if (offset > number.length + 5) return number.length
                    return offset - 5
                }
                if (offset <= 15) {
                    if (offset > number.length + 6) return number.length
                    return offset - 6
                }
                if (offset > number.length) return number.length
                return 10
            }
        }
        return TransformedText(annotatedString, offsetMapping)
    }

    TextField(
        value = number,
        onValueChange = { value -> onValueChange(value) },
        shape = RoundedCornerShape(8.dp),
        visualTransformation = if (isInFocus.value || number.isNotEmpty()) {
            VisualTransformation { numberTransformer() }
        } else VisualTransformation.None,
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = DarkGrey,
            containerColor = LighterGrey,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = modifier
            .height(50.dp)
            .width(343.dp),
        placeholder = {
            Text(
                text = placeholder,
                color = Grey,
                fontFamily = sanFrancisco
            )
        },
        trailingIcon = {
            if (number.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Outlined.Clear,
                    contentDescription = "",
                    tint = DarkGrey,
                    modifier = Modifier
                        .clickable {
                            onClear()
                        }
                )
            }
        }
    )
}


@Composable
@Preview
private fun TextFieldPreview() {
    Column {
        CustomTextField(
            isValid = false,
            text = "1234dd",
            onValueChange = { /*TODO*/ },
            placeholder = "Имя",
            onClear = {},
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
        CustomTextField(
            isValid = true,
            text = "1234dd",
            placeholder = "Имя",
            onClear = {},
            onValueChange = { /*TODO*/ }
        )
    }
}