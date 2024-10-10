package com.app.myapistructure.base

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.gson.Gson
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    abstract val layoutId: Int
    protected lateinit var binding: T

   /* lateinit var loadingDialog: LoadingDialog*/

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    @Inject
    lateinit var gson: Gson

  /*  @Inject
    lateinit var dataStore: MyDataStore*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
      /*  dataStore = MyDataStore(this)
        loadingDialog = LoadingDialog(this)*/

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun notificationPermissionHandle(
        onPermissionGranted: (Boolean) -> Unit,
        onPermissionDenied: () -> Unit
    ) {
        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission(),
        ) { isGranted: Boolean ->
            if (isGranted) {
                onPermissionGranted.invoke(true)
            } else {
                if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                    onPermissionGranted.invoke(false)
                } else {
                    onPermissionDenied.invoke()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun requestNotificationPermission() {
        requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
    }

    protected fun goToPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        startActivity(intent)
        finish()
    }
/*
    fun generateImageUrl(): Pair<Uri, String> {
        @SuppressLint("SimpleDateFormat") val sdf = SimpleDateFormat("yyyy-MM-dd_HH:mm:ss:SSS")
        val cal: Calendar = Calendar.getInstance()
        val s: String = sdf.format(cal.time)
        val string = "${filesDir}/Image-$s.jpg"
        return Pair(
            FileProvider.getUriForFile(
                this, BuildConfig.APPLICATION_ID + ".fileprovider", File(string)
            ), string
        )
    }*/
}