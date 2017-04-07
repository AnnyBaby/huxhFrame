package com.frame.huxh.mvpdemo.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.frame.huxh.mvpdemo.R;
import com.frame.huxh.mvpdemo.bean.ActicleBean;
import com.frame.huxh.mvpdemo.interfacepkg.RefreshViewInterface;
import com.frame.huxh.mvpdemo.pull.BaseViewHolder;
import com.frame.huxh.mvpdemo.pull.PullRecycler;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class ArticleActivity extends BaseListActivity<ActicleBean> implements RefreshViewInterface<ActicleBean>{
    private int page = 1;


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_article);
//        ButterKnife.bind(this);
//    }

    @Override
    protected void setUpContentView(int layoutId) {
        super.setUpContentView(R.layout.activity_article);
        ButterKnife.bind(this);
    }

    @Override
    protected void setUpTitle(int titleResId) {
        super.setUpTitle(R.string.zhihu_daily);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        recycler.setRefreshing();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_layout_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onRefresh(int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }

        if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
            page = 1;
        }

        //请求数据
    }

    @Override
    public void showArticles(List<ActicleBean> list) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    class SampleViewHolder extends BaseViewHolder {

        ImageView mSampleListItemImg;
        TextView mSampleListItemLabel;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.tv_context);
            mSampleListItemImg = (ImageView) itemView.findViewById(R.id.img_article);
        }

        @Override
        public void onBindViewHolder(int position) {
//            List<ActicleBean.StoriesBean> StoriesBean = mDataList.get(0).getStories();
//            ActicleBean.StoriesBean bean = StoriesBean.get(position);
//            mSampleListItemLabel.setText(bean.getTitle());
//            Glide.with(mSampleListItemImg.getContext())
//                    .load(bean.getImages().get(0))
//                    .centerCrop()
//                    .placeholder(R.color.app_primary_color)
//                    .crossFade()
//                    .into(mSampleListItemImg);
        }

        @Override
        public void onItemClick(View view, int position) {

        }

    }

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
            return super.getItemDecoration();
    }
}
