package me.ziem.html_class;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

public class ImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        TextView textView = (TextView) findViewById(R.id.lorem);
        String string = getString(R.string.images_lorem);
        Spanned spanned = Html.fromHtml(string, new ResourcesImageGetter(this), null);
        textView.setText(spanned);
    }

    public static class ResourcesImageGetter implements Html.ImageGetter {
        private Context context;

        public ResourcesImageGetter(Context context) {
            this.context = context;
        }

        @Override
        public Drawable getDrawable(String source) {
            String drawableName = source.substring(0, source.lastIndexOf('.'));
            Drawable drawable = getDrawableByName(drawableName);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
            return drawable;
        }

        private Drawable getDrawableByName(String name) {
            Resources resources = context.getResources();
            String packageName = context.getPackageName();
            final int resourceId = resources.getIdentifier(name, "drawable", packageName);

            return ResourcesCompat.getDrawable(resources, resourceId, context.getTheme());
        }
    }
}
