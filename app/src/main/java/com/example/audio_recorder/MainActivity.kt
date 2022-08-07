package com.example.audio_recorder


import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {

    lateinit var mediaRecorder: MediaRecorder
    lateinit var output: String
    var state by Delegates.notNull<Boolean>()
    var isRecording by Delegates.notNull<Boolean>()
    lateinit var start:Button
    lateinit var stop:Button
    lateinit var pause: Button
    lateinit var mediaPlayer: MediaPlayer
    private val RECORD_REQUEST_CODE = 101
    private val STORAGE_REQUEST_CODE = 102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


    fun onAudio(v: View?) {
        startActivity(Intent(this, activityAudio::class.java))
    }

    fun onVideo(v: View?) {
        startActivity(Intent(this, ActivityVideo::class.java))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
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
 /*       stop = findViewById(R.id.stop)
        start = findViewById(R.id.start)
        pause = findViewById(R.id.puase)
        state = false
        isRecording = false
        mediaPlayer = MediaPlayer()

        audioSetup()

        if (!hasMicrophone()) {
            stop.isEnabled = false
            pause.isEnabled = false
            start.isEnabled = false
        } else {
            pause.isEnabled = false
            stop.isEnabled = false
        }

        output = Environment.getExternalStorageDirectory().absolutePath + "/recording.mp3"
        mediaRecorder = MediaRecorder()

        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)

       /* start.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, permissions,0)
            } else {
                startRecording()
            }
        }

        stop.setOnClickListener{
            stopRecording()
        }

        pause.setOnClickListener {
            pauseRecording()
        }




    }
    fun recordAudio(view: View) {
        isRecording = true
        stop.isEnabled = true
        pause.isEnabled = false
        start.isEnabled = false

        try {
            mediaRecorder = MediaRecorder()
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mediaRecorder.setOutputFile(output)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mediaRecorder.prepare()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mediaRecorder.start()

    }

    fun stopAudio(view: View) {

        stop.isEnabled = false
        pause.isEnabled = true

        if (isRecording) {
            start.isEnabled = false
            mediaRecorder?.stop()
            mediaRecorder?.release()

            isRecording = false
        } else {
            mediaPlayer?.release()

            start.isEnabled = true
        }
    }
    fun playAudio(view: View) {
        pause.isEnabled = false
        start.isEnabled = false
        stop.isEnabled = true

        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(output)
        mediaPlayer?.prepare()
        mediaPlayer?.start()
    }



    fun requestPermission(permissionType: String, requestCode: Int) {
        val permission = ContextCompat.checkSelfPermission(this,
            permissionType)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(permissionType), requestCode
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RECORD_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0]
                    != PackageManager.PERMISSION_GRANTED) {

                    start.isEnabled = false

                    Toast.makeText(this,
                        "Record permission required",
                        Toast.LENGTH_LONG).show()
                } else {
                    requestPermission(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        STORAGE_REQUEST_CODE)
                }
                return
            }
            STORAGE_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0]
                    != PackageManager.PERMISSION_GRANTED) {
                    start.isEnabled = false
                    Toast.makeText(this,
                        "External Storage permission required",
                        Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
     fun audioSetup() {

        if (!hasMicrophone()) {
            stop.isEnabled = false
            pause.isEnabled = false
            start.isEnabled = false
        } else {
            pause.isEnabled = false
            stop.isEnabled = false
        }

        output = Environment.getExternalStorageDirectory()
            .absolutePath + "/myaudio.3gp"

        requestPermission(
            Manifest.permission.RECORD_AUDIO,
            RECORD_REQUEST_CODE)

    }

    fun hasMicrophone(): Boolean {
        val pmanager = this.packageManager
        return pmanager.hasSystemFeature(
            PackageManager.FEATURE_MICROPHONE)
    }
}
*/