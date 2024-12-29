package edu.niu.android.qrcodereader;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    private DatabaseManager db;
    GridLayout grid;
    ScrollView scroll;
    RelativeLayout layout;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        db = new DatabaseManager(this);

        layout = new RelativeLayout(this);
        grid = new GridLayout(this);
        scroll = new ScrollView(this);
        grid.setColumnCount(2);
        scroll.addView(grid);
        layout.addView(scroll);
        //Programmatically create the layouts for the dynamic data.
        //Also create an object from the DatabaseManager class to store and access scanned data.
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        setContentView(layout);//Set the view to the programmatically created layout
        update();//Access
    }

    public void update()
    {
        grid.removeAllViews();//Clear the grid
        ArrayList<Codes> codes = db.selectAll();//Get every item from the DB table.

        if(!codes.isEmpty())
        {

            for(Codes codes1 : codes)
            {
                TextView format = new TextView(this);
                format.setText(codes1.getFormat());
                format.setTextSize(20);
                TextView content = new TextView(this);
                content.setText(" " + codes1.getContent());
                content.setTextSize(20);
                //If the content is a link, make it clickable.
                content.setAutoLinkMask(Linkify.WEB_URLS);
                content.setMovementMethod(LinkMovementMethod.getInstance());

                grid.addView(format);
                grid.addView(content);
            }//Iterate over the scanned codes in the database and add them to the view.
        }
    }


}
