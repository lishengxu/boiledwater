package com.deepindex.attributeset;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String XML_START = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
    private static final String ATTRIBUTE_BEGIN = "<attribute_info>\n";
    private static final String ATTRIBUTE_END = "</attribute_info>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view;

        Bundle bundle = new Bundle();
        bundle.putBoolean("", false);
        bundle.putByte("", (byte) 0);
        bundle.putShort("", (short) 0);
        bundle.putInt("", 0);
        bundle.putLong("", 0L);
        bundle.putFloat("", 0.0f);
        bundle.putDouble("", 0.0);
        bundle.putChar("", '0');
        bundle.putString("", "0");
        bundle.putBundle("", new Bundle());
        bundle.getParcelable("");
        bundle.getBoolean("");
        bundle.getByte("");
        bundle.getShort("");
        bundle.getInt("");
        bundle.getLong("");
        bundle.getFloat("");
        bundle.getDouble("");
        bundle.getChar("");
        bundle.getString("");
        bundle.getString("", "");
        bundle.getBundle("");
        bundle.putParcelable("", new Parcelable() {
            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {

            }
        });
        bundle.getStringArrayList("");
        bundle.putStringArrayList("", new ArrayList<String>());
        bundle.getIntegerArrayList("");
        bundle.putIntegerArrayList("", new ArrayList<Integer>());
//        bundle.putParcelableArrayList();
//        bundle.getParcelableArrayList();
        bundle.clear();
    }
}
