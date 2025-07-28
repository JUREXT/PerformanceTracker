package com.programming.performancetrackersample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.programming.gradledagger.Repository
import com.programming.gradlehilt.TestGradleHilt
import com.programming.nativelogger.NativeLogger.d
import com.programming.performancetracker.PerformanceTracker
import com.programming.performancetrackersample.MainActivity.Companion.LIFECYCLE_LABEL
import com.programming.performancetrackersample.ui.theme.PerformanceTrackerSampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private val performanceTracker = PerformanceTracker

    @Inject
    lateinit var repository: Repository

    companion object {
        const val LIFECYCLE_LABEL = "lifecycle_label"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        performanceTracker.startTracking(label = LIFECYCLE_LABEL)

        lifecycleScope.launch {
            performanceTracker.measure(label = "test") {
                delay(1200)
            }
            d { "WHAT Duration: ${performanceTracker.getDurationForLabelOrNull("test")}" }
        }

        d { "WHAT TestGradleHilt: ${TestGradleHilt.testString()}" }
        d { "WHAT TestDaggerHilt, Repository::init(): ${repository.init()}" }

        setContent {
            PerformanceTrackerSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.LightGray)
                            .padding(innerPadding),
                        onLifecycleClick = {
                            performanceTracker.getDurationForLabelOrNull(label = LIFECYCLE_LABEL)

                            d { "WHAT TestDaggerHilt, Repository::init(): ${repository.init()}" }
                        }
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        performanceTracker.stopTracking(label = LIFECYCLE_LABEL)
    }
}

@Composable
fun MainView(
    modifier: Modifier = Modifier,
    onLifecycleClick: () -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        TextButton(
            text = LIFECYCLE_LABEL.uppercase(),
            onClick = onLifecycleClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    PerformanceTrackerSampleTheme {
        MainView(
            modifier = Modifier
                .fillMaxSize(),
            onLifecycleClick = {}
        )
    }
}

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = TextStyle(
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W800,
            fontStyle = FontStyle.Normal,
            background = Color.LightGray
        ),
        modifier = modifier
            .padding(15.dp)
            .clickable(onClick = onClick, role = Role.Button)
    )
}

@Preview(showBackground = true)
@Composable
fun TextButtonPreview() {
    PerformanceTrackerSampleTheme {
        TextButton(
            text = "Text Button",
            onClick = {}
        )
    }
}