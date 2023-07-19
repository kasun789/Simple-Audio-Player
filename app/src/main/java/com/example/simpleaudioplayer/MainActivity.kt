package com.example.simpleaudioplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var runnable:Runnable
    private var handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaplayer:MediaPlayer = MediaPlayer.create(this,R.raw.newsong)
        var progressbar:SeekBar = findViewById(R.id.seekBar)
        var playbtn: ImageButton= findViewById(R.id.playBtn)

        progressbar.progress = 0
        progressbar.max = mediaplayer.duration

        playbtn.setOnClickListener{
            if(!mediaplayer.isPlaying){
                mediaplayer.start()
                playbtn.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24)
            }
            else{
                mediaplayer.pause()
                playbtn.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
            }
        }

        progressbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, changed: Boolean) {
                if(changed){
                    mediaplayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        runnable = Runnable {
            progressbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable,1000)
        }

        handler.postDelayed(runnable,1000)

        mediaplayer.setOnCompletionListener {
            playbtn.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
            progressbar.progress = 0
        }


    }
}