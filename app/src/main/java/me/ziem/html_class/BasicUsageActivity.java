package me.ziem.html_class;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

public class BasicUsageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        TextView textView = (TextView) findViewById(R.id.lorem);
        String string = getString(R.string.basic_usage_lorem);
        Spanned spanned = Html.fromHtml(string);
        textView.setText(spanned);
    }
}
