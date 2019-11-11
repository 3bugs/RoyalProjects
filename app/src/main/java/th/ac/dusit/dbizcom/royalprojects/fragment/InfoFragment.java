package th.ac.dusit.dbizcom.royalprojects.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import th.ac.dusit.dbizcom.royalprojects.R;
import th.ac.dusit.dbizcom.royalprojects.model.InfoMarker;

public class InfoFragment extends Fragment {

    private static final String ARG_PROJECT_ID = "project_id";

    private RecyclerView mRecyclerView;
    private ImageView mInfoImageView;
    //private View mProgressView;
    private int mProjectId;
    private List<InfoMarker> mInfoMarkerList;

    private InfoFragmentListener mListener;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(int projectId) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PROJECT_ID, projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProjectId = getArguments().getInt(ARG_PROJECT_ID, 0);
            //Utils.showShortToast(getContext(), String.valueOf(mProjectId));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.info_recycler_view);
        mInfoImageView = view.findViewById(R.id.info_image_view);
        //mProgressView = view.findViewById(R.id.progress_view);

        if (mInfoMarkerList == null) {
            doGetInfoMarker();
        } else {
            setupRecyclerView();
        }
    }

    private void doGetInfoMarker() {
        mInfoMarkerList = new ArrayList<>();

        switch (mProjectId) {
            case 1:
                mInfoImageView.setImageResource(R.drawable.info_01_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "จุดลงทะเบียน", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ห้องประชุม", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "อาคารนิทรรศการ", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาท่าตะบูน", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "หอชะคราม", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาท่าน้ำ", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ลานโพธิ์", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดจอดรถ", 0, 0
                ));
                break;
            case 2:
                mInfoImageView.setImageResource(R.drawable.info_02_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาป่าชายเลน", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "พลับพลาปล่อยปู", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาสืบพันธุ์ไม้ป่าชายเลน", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาธรรมชาติ", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ท่าเทียบเรือ", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ทางเดินศึกษาธรรมชาติป่าบก", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ทุ่งโปร่งทอง", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "หอชมเรือนยอดป่าชายเลน", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ป่าตะกาล", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาปูดำ", 0, 0
                ));
                break;
            case 3:
                mInfoImageView.setImageResource(R.drawable.info_03_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "กองอำนวยการ", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดชมวิวอุทยาน", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "พื้นที่ทำโป่งเทียม", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดเพาะพันธุ์ต้นไม้", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดกางเต็นท์", 0, 0
                ));
                break;
            case 4:
                mInfoImageView.setImageResource(R.drawable.info_04_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "สำนักงาน", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "สวนสมุนไพร", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดกางเต็นท์", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ซุ้มไม้เลื้อย", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "เรือนเพาะชำหญ้าแฝก", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ลานจอดรถ", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ฉก. ตำรวจพลร่ม", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "แปลงรวบรวมพันธุ์ไม้หายาก", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "แปลงรวบรวมสายพันธุ์หญ้าแฝก", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "สวนพฤกษศาสตร์", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "บ้านพักรับรอง", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "อ่างเก็บน้ำหญ้าแฝก", 0, 0
                ));
                break;
            case 5:
                mInfoImageView.setImageResource(R.drawable.info_05_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "อ่างเก็บน้ำห้วยมงคล", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดชมวิวอ่างเก็บน้ำห้วยมงคล", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ชลประทาน", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "แปลงเกษตร", 0, 0
                ));
                break;
            case 6:
                mInfoImageView.setImageResource(R.drawable.info_06_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "อ่างเก็บน้ำป่าละอู", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดชิมวิวอ่างเก็บน้ำป่าละอู", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "หน่วยพิทักษ์อุทยานแห่งชาติ", 0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดกางเต็นท์", 0, 0
                ));
                break;
        }
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (getContext() != null) {
            InfoAdapter mAdapter = new InfoAdapter(
                    getContext(),
                    mInfoMarkerList,
                    mListener
            );
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.addItemDecoration(new InfoFragment.SpacingDecoration(getContext()));
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InfoFragmentListener) {
            mListener = (InfoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InfoFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface InfoFragmentListener {
    }

    private static class InfoAdapter extends RecyclerView.Adapter<InfoFragment.InfoAdapter.InfoViewHolder> {

        private final Context mContext;
        private final List<InfoMarker> mInfoMarkerList;
        private final InfoFragmentListener mListener;

        InfoAdapter(Context context, List<InfoMarker> infoMarkerList, InfoFragmentListener listener) {
            mContext = context;
            mInfoMarkerList = infoMarkerList;
            mListener = listener;
        }

        @NonNull
        @Override
        public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_info_button, parent, false
            );
            return new InfoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
            final InfoMarker infoMarker = mInfoMarkerList.get(position);

            holder.mInfoMarker = infoMarker;
            holder.mInfoButton.setText(infoMarker.text);

            /*holder.mNameTextView.setText(project.name);

            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            Glide.with(mContext)
                    .load(ApiClient.IMAGE_BASE_URL.concat(project.coverImage))
                    .placeholder(circularProgressDrawable)
                    .into(holder.mProjectImageView);*/
        }

        @Override
        public int getItemCount() {
            return mInfoMarkerList.size();
        }

        class InfoViewHolder extends RecyclerView.ViewHolder {

            private final View mRootView;
            private final Button mInfoButton;

            private InfoMarker mInfoMarker;

            InfoViewHolder(View itemView) {
                super(itemView);

                mRootView = itemView;
                mInfoButton = itemView.findViewById(R.id.info_button);

                /*mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onClickProject(mInfoMarker);
                    }
                });*/
            }
        }
    }

    public class SpacingDecoration extends RecyclerView.ItemDecoration {

        private final static int MARGIN_TOP_IN_DP = 0;
        private final static int MARGIN_BOTTOM_IN_DP = 16;
        private final int mMarginTop, mMarginBottom;

        SpacingDecoration(@NonNull Context context) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            mMarginTop = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    MARGIN_TOP_IN_DP,
                    metrics
            );
            mMarginBottom = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    MARGIN_BOTTOM_IN_DP,
                    metrics
            );
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            final int itemPosition = parent.getChildAdapterPosition(view);
            if (itemPosition == RecyclerView.NO_POSITION) {
                return;
            }
            if (itemPosition == 0) {
                outRect.top = mMarginTop;
            }
            final RecyclerView.Adapter adapter = parent.getAdapter();
            if ((adapter != null) && (itemPosition == adapter.getItemCount() - 1)) {
                outRect.bottom = mMarginBottom;
            }
        }
    }
}
