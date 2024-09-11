package com.example.lab6;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ContextMenuActivity extends AppCompatActivity {
    private Button contextMenuButton;
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        contextMenuButton = findViewById(R.id.contextMenuButton);
        mainLayout = findViewById(R.id.mainLayout);

        registerForContextMenu(contextMenuButton);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        MenuItems menuItem = MenuItems.fromId(item.getItemId());
        switch (menuItem) {
            case MENU_VANG:
                mainLayout.setBackgroundColor(Color.YELLOW);
                Toast.makeText(this, "màu vàng", Toast.LENGTH_SHORT).show();
                return true;
            case MENU_DO:
                mainLayout.setBackgroundColor(Color.RED);
                Toast.makeText(this, "màu đỏ", Toast.LENGTH_SHORT).show();
                return true;
            case MENU_XANH:
                mainLayout.setBackgroundColor(Color.BLUE);
                Toast.makeText(this, "màu xanh", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
