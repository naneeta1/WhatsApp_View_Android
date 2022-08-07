package com.example.audio_recorder


import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import java.io.File


class ActivityVideo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
    }

    fun onPlayLocalVideo(v: View?) {
        val mVideoView = findViewById<View>(R.id.video_view) as VideoView
        mVideoView.setVideoURI(
            Uri.parse(
                "android.resource://" + packageName + "/"
                        + R.raw.videofile
            )
        )
        mVideoView.setMediaController(MediaController(this))
        mVideoView.requestFocus()
        mVideoView.start()
    }

    fun onStreamVideo(v: View?) {
        val mVideoView = findViewById<View>(R.id.video_view) as VideoView
        mVideoView.setVideoPath("http://techslides.com/demos/sample-videos/small.mp4")
        val mediaController = MediaController(this)
        mediaController.setAnchorView(mVideoView)
        mVideoView.setMediaController(mediaController)
        mVideoView.requestFocus()
        mVideoView.setOnPreparedListener { mVideoView.start() }
    }

    private val VIDEO_CAPTURE = 101
    var videoUri: Uri? = null
    fun onRecordVideo(v: View?) {
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)) {
            val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            val mediaFile =
                File(Environment.getExternalStorageDirectory().absolutePath + "/myvideo.mp4")
            videoUri = Uri.fromFile(mediaFile)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri)
            startActivityForResult(intent, VIDEO_CAPTURE)
        } else {
            Toast.makeText(this, "No camera on device", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == VIDEO_CAPTURE) {
            if (resultCode == RESULT_OK) {
                val mVideoView = findViewById<View>(R.id.video_view) as VideoView
                mVideoView.setVideoURI(videoUri)
                mVideoView.setMediaController(MediaController(this))
                mVideoView.requestFocus()
                mVideoView.start()
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Failed to record video", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.video, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id: Int = item.getItemId()
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}