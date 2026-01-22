package com.full.vermelho;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class FloatingService extends Service {
    private WindowManager wm;
    private View root;

    @Override public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onCreate() {
        super.onCreate();
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        root = LayoutInflater.from(this).inflate(R.layout.floating_menu, null);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        );
        params.gravity = Gravity.CENTER;

        LinearLayout loginArea = root.findViewById(R.id.login_area);
        LinearLayout menuArea = root.findViewById(R.id.menu_area);
        EditText editKey = root.findViewById(R.id.edit_key);
        Button btnLogin = root.findViewById(R.id.btn_login);
        
        Switch swPescoco = root.findViewById(R.id.sw_hs_pescoco);
        Switch swPeito = root.findViewById(R.id.sw_hs_peito);

        btnLogin.setOnClickListener(v -> {
            if (editKey.getText().toString().equals("Mayck771")) {
                loginArea.setVisibility(View.GONE);
                menuArea.setVisibility(View.VISIBLE);
                Toast.makeText(this, "MOD ATIVADO!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "KEY INCORRETA!", Toast.LENGTH_SHORT).show();
            }
        });

        swPescoco.setOnCheckedChangeListener((b, isChecked) -> {
            if (isChecked) { swPeito.setChecked(false); }
        });

        swPeito.setOnCheckedChangeListener((b, isChecked) -> {
            if (isChecked) { swPescoco.setChecked(false); }
        });

        wm.addView(root, params);
    }
                               }
