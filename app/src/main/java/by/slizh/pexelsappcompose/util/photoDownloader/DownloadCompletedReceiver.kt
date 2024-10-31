package by.slizh.pexelsappcompose.util.photoDownloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import javax.inject.Inject

class DownloadCompletedReceiver @Inject constructor() : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == DownloadManager.ACTION_DOWNLOAD_COMPLETE) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            if (id != -1L) {
                Toast.makeText(context, "Download with ID $id finished!", Toast.LENGTH_SHORT).show()
                println("Download with ID $id finished!")
            }
        }
    }
}