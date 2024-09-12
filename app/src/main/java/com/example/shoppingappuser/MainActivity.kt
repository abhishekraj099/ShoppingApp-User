package com.example.shoppingappuser

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoppingappuser.common.TEST_ID
import com.example.shoppingappuser.navigation.App
import com.example.shoppingappuser.screens.utils.NotificationPermissionRequest
import com.example.shoppingappuser.ui.theme.ShoppingAppUserTheme
import com.google.firebase.auth.FirebaseAuth

import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingAppUserTheme {
                NotificationPermissionRequest()
                App(firebaseAuth)
                // HomeScreenUi()
            }
        }
    }
}


//    fun payTest(
//        amount: Int = 1000,
//        name: String = "Test Product",
//        description: String = "Test Description",
//
//        ) {
//
//        /*
//    *  You need to pass the current activity to let Razorpay create CheckoutActivity
//    * */
//        val activity: Activity = this
//        val co = Checkout()
//
//        try {
//            val options = JSONObject()
//            options.put("name", name)
//            options.put("description", description)
//            //You can omit the image option to fetch the image from the dashboard
//            options.put("image", "http://example.com/image/rzp.jpg")
//            options.put("theme.color", "#3399cc");
//            options.put("currency", "INR");
//            options.put("order_id", "order_DBJOWzybf0sJbb");
//            options.put("amount", amount)//pass amount in currency subunits
//
//            val retryObj =  JSONObject ();
//            retryObj.put("enabled", true);
//            retryObj.put("max_count", 4);
//            options.put("retry", retryObj);
//
//            val prefill = JSONObject()
//            prefill.put("email", "abhisraj09870987.@example.com")
//            prefill.put("contact", "9470040429")
//
//            options.put("prefill", prefill)
//            co.open(activity, options)
//        } catch (e: Exception) {
//            Toast.makeText(activity, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
//            e.printStackTrace()
//        }
//
//    }
//
//    override fun onPaymentSuccess(p0: String?) {
//        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onPaymentError(p0: Int, p1: String?) {
//        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show()
//    }
//}