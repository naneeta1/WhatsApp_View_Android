package com.example.audio_recorder


import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException


class activityAudio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)
    }

    fun onPlayLocalAudio(v: View?) {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.d10)
        mediaPlayer.start()
    }

    fun onStreamAudio(v: View?) {
        val url = "https://dl.dropboxusercontent.com/u/10281242/sample_audio.mp3" // your URL here
        val mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        // Listen for if the audio file can't be prepared
        mediaPlayer.setOnErrorListener(object : MediaPlayer.OnErrorListener {
            override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
                // ... react appropriately ...
                // The MediaPlayer has moved to the Error state, must be reset!
                return false
            }
        })
        // Attach to when audio file is prepared for playing
        mediaPlayer.setOnPreparedListener { mediaPlayer.start() }
        // Set the data source to the remote URL
        // Trigger an async preparation which will file listener when completed
        try {
            mediaPlayer.setDataSource(url)
            mediaPlayer.prepareAsync() // might take long! (for buffering, etc)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    var mediaRecorder: MediaRecorder? = null
    var mFileName: String? = null
    fun onRecordAudio(v: View) {
        // Verify that the device has a mic
        val pmanager = this.packageManager
        if (!pmanager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            Toast.makeText(this, "This device doesn't have a mic!", Toast.LENGTH_LONG).show()
            return
        }
        val btnRecord: Button = v as Button
        // Start the recording
        if (v.getTag() === "start" || v.getTag() == null) {
            mFileName = Environment.getExternalStorageDirectory().absolutePath
            mFileName += "/audiorecordtest.3gp"
            mediaRecorder = MediaRecorder()
            mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mediaRecorder!!.setOutputFile(mFileName)
            v.setTag("stop")
            btnRecord.setText("Stop")
            try {
                mediaRecorder!!.prepare()
                mediaRecorder!!.start()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else if (v.getTag() === "stop") {
            mediaRecorder!!.stop()
            mediaRecorder!!.reset()
            mediaRecorder!!.release()
            v.setTag("start")
            btnRecord.setText("Record Audio")
            val mediaPlayer = MediaPlayer()
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
            try {
                mediaPlayer.setDataSource(mFileName)
                mediaPlayer.prepare() // must call prepare first
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } catch (e: SecurityException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            mediaPlayer.start() // then start
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.audio, menu)
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