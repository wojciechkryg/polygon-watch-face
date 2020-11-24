package com.wojdor.polygonwatchface

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import com.google.android.wearable.intent.RemoteIntent
import com.wojdor.common.extension.clearAndAddAll
import com.wojdor.commonandroid.extension.showSnackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val connectedWearables = mutableListOf<Node>()
    private val capabilityChangedListener = { _: CapabilityInfo -> findAllConnectedWearables() }
    private val remoteResultReceiver: ResultReceiver = object : ResultReceiver(Handler()) {
        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            if (resultCode == RemoteIntent.RESULT_OK) {
                showSnackbar(R.string.check_wear_os)
            } else {
                showSnackbar(R.string.cannot_find_devices)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainWatchFace.watchFace.timeColor = getColor(R.color.red_400)
        mainInstallOnWearablesButton.setOnClickListener { onInstallClick() }
    }

    override fun onResume() {
        super.onResume()
        Wearable.getCapabilityClient(this)
            .addListener(capabilityChangedListener, CAPABILITY_WEAR_APP)
        findAllConnectedWearables()
    }

    override fun onPause() {
        super.onPause()
        Wearable.getCapabilityClient(this)
            .removeListener(capabilityChangedListener, CAPABILITY_WEAR_APP)
    }

    private fun onInstallClick() {
        if (connectedWearables.isEmpty()) {
            showSnackbar(R.string.cannot_find_devices)
        } else {
            openStoreOnWearables()
        }
    }

    private fun openStoreOnWearables() {
        val intent = Intent(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .setData(Uri.parse("market://details?id=${packageName}"))
        connectedWearables.forEach {
            RemoteIntent.startRemoteActivity(
                applicationContext,
                intent,
                remoteResultReceiver,
                it.id
            )
        }
    }

    private fun findAllConnectedWearables() {
        Wearable.getNodeClient(this).connectedNodes.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result?.let { connectedWearables.clearAndAddAll(it) }
            }
        }
    }

    companion object {
        private const val CAPABILITY_WEAR_APP = "polygon_watch_face_wear"
    }
}
