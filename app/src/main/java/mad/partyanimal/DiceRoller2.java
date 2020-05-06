package mad.partyanimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class DiceRoller2 extends AppCompatActivity {
    private ImageView imageViewDice1;
    private ImageView imageViewDice2;
    private Random rng1 = new Random();
    private Random rng2 = new Random();


    HomeWatcher mHomeWatcher;
    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder) binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService() {
        bindService(new Intent(this, MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roller2);

        imageViewDice1 = findViewById(R.id.dice1);
        imageViewDice2 = findViewById(R.id.dice2);

        ((View) imageViewDice1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice1();
            }
        });

        ((View) imageViewDice2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice2();
            }
        });

        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }

            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        stopService(music);

    }

    @Override
    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }

    private void rollDice1() {
        int randomNumber = rng1.nextInt(6) + 1;

        switch (randomNumber) {
            case 1:
                imageViewDice1.setImageResource(R.raw.dice1);
                break;
            case 2:
                imageViewDice1.setImageResource(R.raw.dice2);
                break;
            case 3:
                imageViewDice1.setImageResource(R.raw.dice3);
                break;
            case 4:
                imageViewDice1.setImageResource(R.raw.dice4);
                break;
            case 5:
                imageViewDice1.setImageResource(R.raw.dice5);
                break;
            case 6:
                imageViewDice1.setImageResource(R.raw.dice6);
                break;
        }
    }

    private void rollDice2() {
        int randomNumber = rng2.nextInt(6) + 1;

        switch (randomNumber) {
            case 1:
                imageViewDice2.setImageResource(R.raw.dice1);
                break;
            case 2:
                imageViewDice2.setImageResource(R.raw.dice2);
                break;
            case 3:
                imageViewDice2.setImageResource(R.raw.dice3);
                break;
            case 4:
                imageViewDice2.setImageResource(R.raw.dice4);
                break;
            case 5:
                imageViewDice2.setImageResource(R.raw.dice5);
                break;
            case 6:
                imageViewDice2.setImageResource(R.raw.dice6);
                break;
        }
    }

    public void rollDiceAll(View view) {
        rollDice1();
        rollDice2();
    }
}
