package com.wojdor.polygonwatchface

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.wear.remote.interactions.RemoteActivityHelper
import com.google.android.gms.wearable.CapabilityInfo
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import com.wojdor.common.extension.clearAndAddAll
import com.wojdor.common.extension.nextListItem
import com.wojdor.commonandroid.extension.getIntList
import com.wojdor.commonandroid.extension.showSnackbar
import com.wojdor.polygonwatchface.broadcast.TimeChangedReceiver
import com.wojdor.polygonwatchface.databinding.ActivityMainBinding
import com.wojdor.polygonwatchface.util.IntervalExecutor
import java.util.concurrent.Executors
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val connectedWearables = mutableListOf<Node>()
    private val capabilityChangedListener = { _: CapabilityInfo -> findAllConnectedWearables() }
    private val timeChangedReceiver = TimeChangedReceiver()
    private val intervalExecutor =
        IntervalExecutor(CHANGE_WATCH_FACE_TIME_INTERVAL) { randomizeWatchFace() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timeChangedReceiver.onTimeChanged = { binding.mainWatchFace.refreshTime() }
        randomizeWatchFace()
        binding.mainInstallOnWearablesButton.setOnClickListener { onInstallClick() }
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(
                timeChangedReceiver,
                timeChangedReceiver.intentFilter,
                RECEIVER_NOT_EXPORTED
            )
        } else {
            registerReceiver(
                timeChangedReceiver,
                timeChangedReceiver.intentFilter
            )
        }
    }

    override fun onResume() {
        super.onResume()
        Wearable.getCapabilityClient(this)
            .addListener(capabilityChangedListener, CAPABILITY_WEAR_APP)
        findAllConnectedWearables()
        intervalExecutor.start()
    }

    override fun onPause() {
        super.onPause()
        Wearable.getCapabilityClient(this)
            .removeListener(capabilityChangedListener, CAPABILITY_WEAR_APP)
        intervalExecutor.stop()
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(timeChangedReceiver)
    }

    private fun randomizeWatchFace() {
        with(binding.mainWatchFace) {
            timeColor =
                Random.nextListItem(getIntList(R.array.configuration_time_colors))
            isOutline = Random.nextBoolean()
            refreshTime()
        }
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
        RemoteActivityHelper(this)
            .startRemoteActivity(intent)
            .addListener(
                { showSnackbar(R.string.check_wear_os) },
                Executors.newSingleThreadExecutor()
            )
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
        private const val CHANGE_WATCH_FACE_TIME_INTERVAL = 1500L
    }
}
