package com.rekoj134.someproblemsandhowtosolve

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object SetRingToneSound {
    // add permission <uses-permission android:name="android.permission.WRITE_SETTINGS" /> and request
    fun setRingTone(context: Context) {
        try {
            val internalStorageDir = context.filesDir

            // Create a new file in the internal storage directory.
            val ringtoneFile = File(internalStorageDir, "Cute.mp3")
            Log.e("ANCUTKO", ringtoneFile.path.toString())
            if (ringtoneFile.exists()) ringtoneFile.delete()
            // Copy the audio data from the asset file to the new file.
            val inputStream = context.assets.openFd("musics/Cute.mp3").createInputStream()
            val outputStream = FileOutputStream(ringtoneFile)
            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()

            // Set the ringtone using the RingtoneManager.
            RingtoneManager.setActualDefaultRingtoneUri(
                context,
                RingtoneManager.TYPE_ALARM,
                Uri.fromFile(ringtoneFile)
            )
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("ANCUTKO", e.message.toString())
            return
        }
    }
}