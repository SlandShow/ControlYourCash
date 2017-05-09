package com.test_apps.slandshow.controlyourcash;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.balram.locker.utils.Locker;
import com.balram.locker.view.AppLocker;
import com.balram.locker.view.LockActivity;
import com.test_apps.slandshow.controlyourcash.view.CashSettingsActivity;

import java.io.FileOutputStream;


public class MainActivity extends LockActivity implements View.OnClickListener {

    private Button btnOff;
    private Button btnChange;
    private Button btnCteateCash;
    private Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Кнопка отключения пинкода
        btnOff = (Button) findViewById(R.id.bt_on_off);
        // Кнопка включения пинкода
        btnChange = (Button) findViewById(R.id.bt_change);
        // Кнопка создания кошелька
        btnCteateCash = (Button) findViewById(R.id.button_create_cash);
        // Кнопка настроек
        btnSettings = (Button) findViewById(R.id.btn_settings);

        // Закрепление обработчиков событий
        btnOff.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnChange.setText(R.string.change_passcode);
        btnCteateCash.setOnClickListener(this);

        updateUI();
    }


    @Override
    public void onClick(View v) {
        if (v.equals(btnOff)) {
            int type = AppLocker.getInstance().getAppLock().isPasscodeSet() ? Locker.DISABLE_PASSLOCK : Locker.ENABLE_PASSLOCK;
            Intent intent = new Intent(this, LockActivity.class);
            intent.putExtra(Locker.TYPE, type);
            startActivityForResult(intent, type);
        } else if (v.equals(btnChange)) {
            Intent intent = new Intent(this, LockActivity.class);
            intent.putExtra(Locker.TYPE, Locker.CHANGE_PASSWORD);
            intent.putExtra(Locker.MESSAGE, getString(R.string.enter_old_passcode));
            startActivityForResult(intent, Locker.CHANGE_PASSWORD);
            // Создание счёта
        } else if (v.equals(btnCteateCash)) {
            Intent intent = new Intent(this, CashSettingsActivity.class);
            startActivity(intent);
            // Настройки
        } else if (v.equals(btnSettings)) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Locker.DISABLE_PASSLOCK:
                break;
            case Locker.ENABLE_PASSLOCK:
            case Locker.CHANGE_PASSWORD:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, getString(R.string.setup_passcode), Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
        updateUI();

    }


    private void updateUI() {
        if (AppLocker.getInstance().getAppLock().isPasscodeSet()) {
            btnOff.setText(R.string.disable_passcode);
            btnChange.setEnabled(true);
        } else {
            btnOff.setText(R.string.enable_passcode);
            btnChange.setEnabled(false);
        }
    }


}

