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

public class PokerDraw extends AppCompatActivity {

    private ImageView imageViewPoker;
    private Random rng = new Random();

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
        setContentView(R.layout.activity_poker_draw);

        imageViewPoker = findViewById(R.id.poker);
        imageViewPoker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawPoker();
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

    private void drawPoker() {
        int randomNumber = rng.nextInt(52) + 1;
        switch (randomNumber) {
            case 1:
                imageViewPoker.setImageResource(R.raw.poker1);
                break;
            case 2:
                imageViewPoker.setImageResource(R.raw.poker2);
                break;
            case 3:
                imageViewPoker.setImageResource(R.raw.poker3);
                break;
            case 4:
                imageViewPoker.setImageResource(R.raw.poker4);
                break;
            case 5:
                imageViewPoker.setImageResource(R.raw.poker5);
                break;
            case 6:
                imageViewPoker.setImageResource(R.raw.poker6);
                break;
            case 7:
                imageViewPoker.setImageResource(R.raw.poker7);
                break;
            case 8:
                imageViewPoker.setImageResource(R.raw.poker8);
                break;
            case 9:
                imageViewPoker.setImageResource(R.raw.poker9);
                break;
            case 10:
                imageViewPoker.setImageResource(R.raw.poker10);
                break;
            case 11:
                imageViewPoker.setImageResource(R.raw.poker11);
                break;
            case 12:
                imageViewPoker.setImageResource(R.raw.poker12);
                break;
            case 13:
                imageViewPoker.setImageResource(R.raw.poker13);
                break;
            case 14:
                imageViewPoker.setImageResource(R.raw.poker14);
                break;
            case 15:
                imageViewPoker.setImageResource(R.raw.poker15);
                break;
            case 16:
                imageViewPoker.setImageResource(R.raw.poker16);
                break;
            case 17:
                imageViewPoker.setImageResource(R.raw.poker17);
                break;
            case 18:
                imageViewPoker.setImageResource(R.raw.poker18);
                break;
            case 19:
                imageViewPoker.setImageResource(R.raw.poker19);
                break;
            case 20:
                imageViewPoker.setImageResource(R.raw.poker20);
                break;
            case 21:
                imageViewPoker.setImageResource(R.raw.poker21);
                break;
            case 22:
                imageViewPoker.setImageResource(R.raw.poker22);
                break;
            case 23:
                imageViewPoker.setImageResource(R.raw.poker23);
                break;
            case 24:
                imageViewPoker.setImageResource(R.raw.poker24);
                break;
            case 25:
                imageViewPoker.setImageResource(R.raw.poker25);
                break;
            case 26:
                imageViewPoker.setImageResource(R.raw.poker26);
                break;
            case 27:
                imageViewPoker.setImageResource(R.raw.poker27);
                break;
            case 28:
                imageViewPoker.setImageResource(R.raw.poker28);
                break;
            case 29:
                imageViewPoker.setImageResource(R.raw.poker29);
                break;
            case 30:
                imageViewPoker.setImageResource(R.raw.poker30);
                break;
            case 31:
                imageViewPoker.setImageResource(R.raw.poker31);
                break;
            case 32:
                imageViewPoker.setImageResource(R.raw.poker32);
                break;
            case 33:
                imageViewPoker.setImageResource(R.raw.poker33);
                break;
            case 34:
                imageViewPoker.setImageResource(R.raw.poker34);
                break;
            case 35:
                imageViewPoker.setImageResource(R.raw.poker35);
                break;
            case 36:
                imageViewPoker.setImageResource(R.raw.poker36);
                break;
            case 37:
                imageViewPoker.setImageResource(R.raw.poker37);
                break;
            case 38:
                imageViewPoker.setImageResource(R.raw.poker38);
                break;
            case 39:
                imageViewPoker.setImageResource(R.raw.poker39);
                break;
            case 40:
                imageViewPoker.setImageResource(R.raw.poker40);
                break;
            case 41:
                imageViewPoker.setImageResource(R.raw.poker41);
                break;
            case 42:
                imageViewPoker.setImageResource(R.raw.poker42);
                break;
            case 43:
                imageViewPoker.setImageResource(R.raw.poker43);
                break;
            case 44:
                imageViewPoker.setImageResource(R.raw.poker44);
                break;
            case 45:
                imageViewPoker.setImageResource(R.raw.poker45);
                break;
            case 46:
                imageViewPoker.setImageResource(R.raw.poker46);
                break;
            case 47:
                imageViewPoker.setImageResource(R.raw.poker47);
                break;
            case 48:
                imageViewPoker.setImageResource(R.raw.poker48);
                break;
            case 49:
                imageViewPoker.setImageResource(R.raw.poker49);
                break;
            case 50:
                imageViewPoker.setImageResource(R.raw.poker50);
                break;
            case 51:
                imageViewPoker.setImageResource(R.raw.poker51);
                break;
            case 52:
                imageViewPoker.setImageResource(R.raw.poker52);
                break;
        }
    }
}