package com.lx.lxdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.lx.lxdemo.R;
import com.lx.lxlibrary.adapter.BaseRecyclerViewAdapter;
import com.lx.lxlibrary.bean.SortModel;
import com.lx.lxlibrary.utlis.CharacterParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 李响
 * 创建日期 2017/3/1
 * 描述：
 */
public class SelecorCityAdapter<T extends SortModel> extends BaseRecyclerViewAdapter<T> implements SectionIndexer{

    public SelecorCityAdapter(Context context) {
        super(context);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent) {
        return new ItemViewHolder(Inflate(R.layout.adapter_selecter_city, parent));
    }

    class ItemViewHolder extends BaseitemViewHolder {
        private TextView tv_name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        }

        @Override
        protected void initValue(SortModel bean, int position) {
            tv_name.setText(bean.getName());
        }

        @Override
        protected ArrayList<View> bindListenerViews(ArrayList<View> list) {
            return list;
        }

        @Override
        protected void initView() {

        }
    }

    /**
     * 排序加转换拼音
     *
     * @param list
     * @return
     */
    private List<T> filledData(List<T> list) {
        CharacterParser characterParser = CharacterParser.getInstance();
        for (int i = 0; i < list.size(); i++) {
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(list.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                list.get(i).setSortLetters(sortString.toUpperCase());
            } else {
                list.get(i).setSortLetters("#");
            }
        }
        PinyinComparator pinyinComparator = new PinyinComparator();  //实例化汉字转拼音类
        Collections.sort(list, pinyinComparator);
        return list;
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        for (int i = 0; i < getList().size(); i++) {
            String sortStr = getList().get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == sectionIndex) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    @Override
    public int getSectionForPosition(int position) {
       return 0;
    }


    private class PinyinComparator implements Comparator<SortModel> {
        public int compare(SortModel o1, SortModel o2) {
            if (o1.getSortLetters().equals("@")
                    || o2.getSortLetters().equals("#")) {
                return -1;
            } else if (o1.getSortLetters().equals("#")
                    || o2.getSortLetters().equals("@")) {
                return 1;
            } else {
                return o1.getSortLetters().compareTo(o2.getSortLetters());
            }
        }
    }

    @Override
    public void setList(List<T> list) {
        List<T> list1 = filledData(list);
        super.setList(list1);
    }
}
