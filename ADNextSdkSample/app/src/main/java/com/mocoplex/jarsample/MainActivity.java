package com.mocoplex.jarsample;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.mocoplex.adnext.AdlibManager;
import com.mocoplex.jarsample.banner.BannerDynamicActivity;
import com.mocoplex.jarsample.interstitial.IntersDynamicActivity;

import java.util.ArrayList;

public class MainActivity extends ListActivity {

    private AdlibManager adlibManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Glide Library
        Glide.get(this).setMemoryCategory(MemoryCategory.HIGH);

        // 샘플 프로젝트 화면 구성
        initLayout();
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        Intent intent = null;

        TestListItem item = (TestListItem) view.getTag();
        switch (item) {
            case BANNER_DYNAMIC:
                intent = new Intent(this, BannerDynamicActivity.class);
                break;
            case INTERSTITIAL_DYNAMIC:
                intent = new Intent(this, IntersDynamicActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    // Sample layout
    private void initLayout() {

        ArrayList<TestListItem> itemList = new ArrayList<TestListItem>();

        // 띠배너 샘플
        itemList.add(TestListItem.HEADER);
        itemList.add(TestListItem.BANNER_DYNAMIC);
        itemList.add(TestListItem.INTERSTITIAL_DYNAMIC);

        AdlibTestListAdapter adapter = new AdlibTestListAdapter(this, itemList);
        this.setListAdapter(adapter);
    }

    public class AdlibTestListAdapter extends BaseAdapter {
        private final int HEADER = 0;
        private final int ITEM = 1;

        private Context context;
        private ArrayList<TestListItem> list;

        public AdlibTestListAdapter(Context context, ArrayList<TestListItem> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {

            if (list.get(position).isHeader()) {
                return HEADER;
            } else {
                return ITEM;
            }
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TestListItem data = list.get(position);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(getItemViewType(position) == HEADER ? R.layout.main_list_header : R.layout.main_list_item, null);
            }

            TextView txtItem = (TextView) convertView.findViewById(R.id.text);
            txtItem.setText(data.getValue());
            convertView.setTag(data);
            return convertView;
        }
    }
}
