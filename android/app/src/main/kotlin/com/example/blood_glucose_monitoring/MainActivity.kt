package com.example.blood_glucose_monitoring

import android.os.Bundle
import com.veepoo.protocol.listener.data.ICustomSettingDataListener
import com.veepoo.protocol.model.datas.CustomSettingData
import com.veepoo.protocol.VPOperateManager
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.example/bloodGlucose"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            when (call.method) {
                "startBloodGlucoseMonitor" -> {
                    startBloodGlucoseMonitoring()
                    result.success(null)
                }
                else -> result.notImplemented()
            }
        }
    }

    private fun startBloodGlucoseMonitoring() {
        VPOperateManager.getInstance().changeCustomSetting(null, object : ICustomSettingDataListener {
            override fun OnSettingDataChange(customSettingData: CustomSettingData) {
                val bloodGlucoseDetection = customSettingData.bloodGlucoseDetection
                val bloodGlucoseUnit = customSettingData.bloodGlucoseUnit
                // Handle blood glucose settings here
            }
        }, customSetting)
    }
}
