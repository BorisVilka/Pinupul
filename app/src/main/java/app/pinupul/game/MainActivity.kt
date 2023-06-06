package app.pinupul.game

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    public lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        player = MediaPlayer.create(this,R.raw.bg)
        player.setOnCompletionListener {
            it.start()
        }
        if(getSharedPreferences("prefs",Context.MODE_PRIVATE).getBoolean("music",false)) player.start()
        setContentView(R.layout.activity_main)
    }
}