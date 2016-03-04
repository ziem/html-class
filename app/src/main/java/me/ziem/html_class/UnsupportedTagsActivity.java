package me.ziem.html_class;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

import org.xml.sax.XMLReader;

public class UnsupportedTagsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        TextView textView = (TextView) findViewById(R.id.lorem);
        String string = getString(R.string.unsupported_tags_lorem);
        Spanned spanned = Html.fromHtml(string, null, new StrikeTagHandler());
        textView.setText(spanned);
    }

    private static class StrikeTagHandler implements Html.TagHandler {
        @Override
        public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
            if (tag.equalsIgnoreCase("strike") || tag.equalsIgnoreCase("s") || tag.equalsIgnoreCase("del")) {
                handleStrike(opening, output);
            }
        }

        private void handleStrike(boolean opening, Editable output) {
            int length = output.length();
            if (opening) {
                startStrike(output, length);
            } else {
                endStrike(output, length);
            }
        }

        private void startStrike(Editable output, int length) {
            output.setSpan(new StrikethroughSpan(), length, length, Spannable.SPAN_MARK_MARK);
        }

        private void endStrike(Editable output, int length) {
            Object obj = getLast(output, StrikethroughSpan.class);
            int where = output.getSpanStart(obj);

            output.removeSpan(obj);

            if (where != length) {
                output.setSpan(new StrikethroughSpan(), where, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        private Object getLast(Editable text, Class kind) {
            Object[] objects = text.getSpans(0, text.length(), kind);

            if (objects.length == 0) {
                return null;
            } else {
                return objects[objects.length - 1];
            }
        }
    }
}
