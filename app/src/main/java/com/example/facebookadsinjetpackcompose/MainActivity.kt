package com.example.facebookadsinjetpackcompose

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.facebookadsinjetpackcompose.ui.theme.FacebookAdsInJetpackComposeTheme
import com.facebook.ads.AdSize
import com.facebook.ads.AdView

private var adView: AdView? = null
class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AudienceNetworkInitializeHelper.initialize(this)
        setContent {
            FacebookAdsInJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    FacebookBannerAdsView("IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FacebookAdsInJetpackComposeTheme {

    }
}


@Composable
fun FacebookBannerAdsView(bannerId : String) {
    AndroidView(
        factory = { context ->
            // Create and configure your Android View here
            val view = LayoutInflater.from(context).inflate(R.layout.facebook_ads, null, false)
            val bannerContainer = view.findViewById<LinearLayout>(R.id.banner_container)
            adView = AdView(context, bannerId, AdSize.BANNER_HEIGHT_50)
            bannerContainer.addView(adView)
            // Request an ad
            adView!!.loadAd()
            // do whatever you want...
            view
        },
        update = { view ->
            // Update the Android View if needed
        }
    )
}