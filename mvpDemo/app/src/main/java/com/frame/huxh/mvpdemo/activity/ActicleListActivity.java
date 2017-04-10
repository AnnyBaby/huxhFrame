package com.frame.huxh.mvpdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frame.huxh.mvpdemo.R;
import com.frame.huxh.mvpdemo.bean.ActicleBean;
import com.frame.huxh.mvpdemo.bean.ArticleListBean;
import com.frame.huxh.mvpdemo.interfacepkg.RefreshViewInterface;
import com.frame.huxh.mvpdemo.presenter.ArticleListPresenter;
import com.frame.huxh.mvpdemo.pull.BaseListAdapter;
import com.frame.huxh.mvpdemo.pull.BaseViewHolder;
import com.frame.huxh.mvpdemo.pull.DividerItemDecoration;
import com.frame.huxh.mvpdemo.pull.PullRecycler;
import com.frame.huxh.mvpdemo.pull.layoutmanager.ILayoutManager;
import com.frame.huxh.mvpdemo.pull.layoutmanager.MyLinearLayoutManager;
import com.frame.huxh.mvpdemo.rxbus.RxBus;
import com.frame.huxh.mvpdemo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ActicleListActivity extends AppCompatActivity implements RefreshViewInterface<ArticleListBean>,PullRecycler.OnRecyclerRefreshListener {
    @BindView(R.id.image_top)
    ImageView mImageTop;
    @BindView(R.id.editor)
    TextView mEditor;
    @BindView(R.id.pullRecycler)
    PullRecycler mRecycler;
    private String mTitle;
    private int page = 1;
    private String mID;
    private ArticleListPresenter mArticleListPresenter;
    private List<ArticleListBean.StoriesBean> mDataList;
    private BaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acticle_list);
//        ButterKnife.bind(this);
        operateBus();
//        setUpData();

    }

    protected void setUpData() {
        adapter = new ListAdapter();
        mRecycler.setOnRefreshListener(this);
        mRecycler.setLayoutManager(getLayoutManager());
        mRecycler.addItemDecoration(getItemDecoration());
        mRecycler.setAdapter(adapter);
    }

    protected ILayoutManager getLayoutManager() {
        return new MyLinearLayoutManager(getApplicationContext());
    }

    protected RecyclerView.ItemDecoration getItemDecoration() {
        return new DividerItemDecoration(getApplicationContext(), R.drawable.list_divider);
    }
    /**
     * RxBus
     */
    private void operateBus() {
        RxBus.get().toObservable()
                .map(new Function<Object, ActicleBean.OthersBean>() {
                    @Override
                    public ActicleBean.OthersBean apply(@NonNull Object o) throws Exception {
                        return (ActicleBean.OthersBean) o;
                    }

                })
                .subscribe(new Consumer<ActicleBean.OthersBean>() {
                    @Override
                    public void accept(@NonNull ActicleBean.OthersBean othersBean) throws Exception {
                        if (othersBean != null) {
                            ToastUtils.toast(ActicleListActivity.this, "othersBean" + othersBean.getDescription());
//                            Glide.with(mImageTop.getContext())
//                                    .load(othersBean.getThumbnail())
//                                    .centerCrop()
//                                    .placeholder(R.color.app_primary_color)
//                                    .crossFade()
//                                    .into(mImageTop);
//                            mTitle = othersBean.getName();
//                            mID = String.valueOf(othersBean.getId());

//                            mArticleListPresenter = new ArticleListPresenter(ActicleListActivity.this,mID);
//                            onRefresh(1);

                        }
                    }

                });
    }


    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_layout_item, parent, false);
        return new SampleViewHolder(view);
    }


    public void onRefresh(int action) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        if (action == PullRecycler.ACTION_PULL_TO_REFRESH) {
            page = 1;
        }
        mArticleListPresenter.fetchArticleLists();
    }


    @Override
    public void showData(ArticleListBean articleListBean) {
        if (articleListBean.getEditors().size() > 0) {
            mEditor.setText("主编  " + articleListBean.getEditors().get(0).getName());
        }
        mDataList.clear();
        mDataList.addAll(articleListBean.getStories());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        mRecycler.setRefreshing();
    }

    @Override
    public void hideLoading() {
        mRecycler.onRefreshCompleted();
    }

    class SampleViewHolder extends BaseViewHolder {

        ImageView mSampleListItemImg;
        TextView mSampleListItemLabel;
        TextView mTvDaily;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mSampleListItemLabel = (TextView) itemView.findViewById(R.id.tv_context);
            mSampleListItemImg = (ImageView) itemView.findViewById(R.id.img_article);
            mTvDaily = (TextView) itemView.findViewById(R.id.tv_dailytype);
        }

        @Override
        public void onBindViewHolder(int position) {
            ArticleListBean.StoriesBean bean = mDataList.get(position);
            mTvDaily.setVisibility(View.GONE);
            mSampleListItemLabel.setText(bean.getTitle());
            if (bean.getImages().size() > 0) {
                Glide.with(mSampleListItemImg.getContext())
                        .load(bean.getImages())
                        .centerCrop()
                        .placeholder(R.color.app_primary_color)
                        .crossFade()
                        .into(mSampleListItemImg);
            } else {
                mSampleListItemImg.setVisibility(View.GONE);
            }

        }

        @Override
        public void onItemClick(View view, int position) {
//            ToastUtils.toast(ArticleActivity.this,"position"+position);
//            ArticleListBean.StoriesBean bean = mDataList.get(position);
        }

    }



    public class ListAdapter extends BaseListAdapter {

        @Override
        protected BaseViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent, viewType);
        }

        @Override
        protected int getDataCount() {
            return mDataList != null ? mDataList.size() : 0;
        }

        @Override
        protected int getDataViewType(int position) {
            return getItemType(position);
        }

        @Override
        public boolean isSectionHeader(int position) {
            return ActicleListActivity.this.isSectionHeader(position);
        }
    }
    protected boolean isSectionHeader(int position) {
        return false;
    }
    protected int getItemType(int position) {
        return 0;
    }

}