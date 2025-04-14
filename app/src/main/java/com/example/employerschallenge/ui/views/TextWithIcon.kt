package com.example.employerschallenge.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.employerschallenge.R
import com.example.employerschallenge.core.extensions.empty
import com.example.employerschallenge.ui.theme.EmployeesChallengeTheme

@Composable
fun TextWithIcon(
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color? = null,
    icon: ImageVector? = null,
    text: String,
) {

    Spacer(modifier = Modifier.height(20.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon?.let {
            Icon(
                modifier = Modifier.size(20.dp),
                contentDescription = String.empty(),
                tint = color ?: MaterialTheme.colorScheme.onSurface,
                imageVector = icon,
            )
        }

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            style = style,
            color = color ?: MaterialTheme.colorScheme.onSurface,
            text = text
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    HorizontalDivider()
}

@Preview(showBackground = true)
@Composable
fun TextWithIconPreview() {
    EmployeesChallengeTheme {
        Column {
            TextWithIcon(
                icon = Icons.Default.Face,
                text = stringResource(id = R.string.id, 10)
            )
        }
    }
}
