package com.frame.huxh.evenbusdemo.evenbus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frame.huxh.evenbusdemo.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestEvenBusFragment extends Fragment {


    @BindView(R.id.test_evenbus_recycleview)
    RecyclerView mTestEvenbusRecycleview;
    Unbinder unbinder;
    View mView;
    private List mList = new ArrayList<>();
    private TestEvenBusAdapter mTestEvenBusAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        if(mView == null){
            mView = inflater.inflate(R.layout.fragment_test_even_bus, container, false);
//        }
        unbinder = ButterKnife.bind(this, mView);
        initRecycleView();
        initData();
        return mView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private  void  initRecycleView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTestEvenBusAdapter = new TestEvenBusAdapter();
        mTestEvenbusRecycleview.setLayoutManager(layoutManager);
        mTestEvenbusRecycleview.setAdapter(mTestEvenBusAdapter);
        //设置Item增加、移除动画
        mTestEvenbusRecycleview.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        mTestEvenbusRecycleview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mTestEvenBusAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                EventBus.getDefault().post(new MessageEvent("我从TestEvenBusFragment来的"+mList.get(position) ,0));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                EventBus.getDefault().post(new OtherMessageEven(mList,"长按哦!我从TestEvenBusFragment来的"+mList.get(position),0));
            }
        });
    }

    private void  initData(){
        for (int i=0;i<10;i++){
            mList.add("item"+i);
        }
        mTestEvenBusAdapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    private class TestEvenBusAdapter extends RecyclerView.Adapter<TestEvenBusAdapter.MyViewHolder>{



        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
        {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    getActivity()).inflate(R.layout.test_evenbus_list_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.tv.setText(mList.get(position)+"");

            if (mOnItemClickLitener != null)
            {
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                        return ;
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder{

            TextView tv;
            public MyViewHolder(View view)
            {
                super(view);
                tv = (TextView) view.findViewById(R.id.recycle_tv);

            }
        }
    }
}
