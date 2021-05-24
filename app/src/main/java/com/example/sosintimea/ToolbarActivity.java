package bxstudio.toolbartutorial;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.sosintimea.R;


/**
 * Created by Onilax on 09/04/2017.
 */

public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

}