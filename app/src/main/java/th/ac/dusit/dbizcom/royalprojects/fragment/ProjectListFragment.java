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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import th.ac.dusit.dbizcom.royalprojects.R;
import th.ac.dusit.dbizcom.royalprojects.etc.Utils;
import th.ac.dusit.dbizcom.royalprojects.model.Project;
import th.ac.dusit.dbizcom.royalprojects.net.ApiClient;
import th.ac.dusit.dbizcom.royalprojects.net.GetProjectResponse;
import th.ac.dusit.dbizcom.royalprojects.net.MyRetrofitCallback;
import th.ac.dusit.dbizcom.royalprojects.net.WebServices;

public class ProjectListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private View mProgressView;
    private List<Project> mProjectList;

    private ProjectListFragmentListener mListener;

    public ProjectListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.projects_recycler_view);
        mProgressView = view.findViewById(R.id.progress_view);

        if (mProjectList == null) {
            doGetProject();
        } else {
            setupRecyclerView();
        }
    }

    private void doGetProjectOffline() {
        mProjectList = new ArrayList<>();
        mProjectList.add(new Project(
                1, "โครงการพระราชดำริ 1", "ที่อยู่ 1",
                "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด ",
                "081-234-5678", "8.00 - 18.00 น.", 13.7563, 100.5018, null, null
        ));
        mProjectList.add(new Project(
                2, "โครงการพระราชดำริ 2", "ที่อยู่ 2",
                "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด ",
                "081-234-5678", "8.00 - 18.00 น.", 13.7563, 100.5018, null, null
        ));
        mProjectList.add(new Project(
                3, "โครงการพระราชดำริ 3", "ที่อยู่ 3",
                "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด ",
                "081-234-5678", "8.00 - 18.00 น.", 13.7563, 100.5018, null, null
        ));
        mProjectList.add(new Project(
                4, "โครงการพระราชดำริ 4", "ที่อยู่ 4",
                "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด "
                        + "รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด รายละเอียด ",
                "081-234-5678", "8.00 - 18.00 น.", 13.7563, 100.5018, null, null
        ));

        setupRecyclerView();
    }

    private void doGetProject() {
        mProgressView.setVisibility(View.VISIBLE);

        Retrofit retrofit = ApiClient.getClient();
        WebServices services = retrofit.create(WebServices.class);

        Call<GetProjectResponse> call = services.getProject();
        call.enqueue(new MyRetrofitCallback<>(
                getActivity(),
                null,
                mProgressView,
                new MyRetrofitCallback.MyRetrofitCallbackListener<GetProjectResponse>() {
                    @Override
                    public void onSuccess(GetProjectResponse responseBody) {
                        mProjectList = responseBody.projectList;
                        setupRecyclerView();
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Utils.showOkDialog(
                                getActivity(),
                                "Error",
                                errorMessage,
                                null
                        );
                    }
                }
        ));
    }

    private void setupRecyclerView() {
        if (getContext() != null) {
            ProjectListAdapter mAdapter = new ProjectListAdapter(
                    getContext(),
                    mProjectList,
                    mListener
            );
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.addItemDecoration(new SpacingDecoration(getContext()));
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProjectListFragmentListener) {
            mListener = (ProjectListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ProjectListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ProjectListFragmentListener {
        void onClickProject(Project Project);

        void onClickAboutButton();
    }

    private static class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.BaseViewHolder> {

        private static final int VIEW_TYPE_NORMAL = 0;
        private static final int VIEW_TYPE_BUTTON = 1;

        private final Context mContext;
        private final List<Project> mProjectList;
        private final ProjectListFragmentListener mListener;

        ProjectListAdapter(Context context, List<Project> ProjectList, ProjectListFragmentListener listener) {
            mContext = context;
            mProjectList = ProjectList;
            mListener = listener;
        }

        @Override
        public int getItemViewType(int position) {
            return position == mProjectList.size() ? VIEW_TYPE_BUTTON : VIEW_TYPE_NORMAL;
        }

        @NonNull
        @Override
        public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_NORMAL) {
                View view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_project, parent, false
                );
                return new ProjectViewHolder(view);
            } else {
                View view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_about_button, parent, false
                );
                return new AboutButtonViewHolder(view);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull BaseViewHolder h, int position) {
            if (position == mProjectList.size()) {
                AboutButtonViewHolder holder = (AboutButtonViewHolder) h;
                holder.mButton.setText("ผู้พัฒนา");
            } else {
                final Project project = mProjectList.get(position);

                ProjectViewHolder holder = (ProjectViewHolder) h;
                holder.mProject = project;
                holder.mNameTextView.setText(project.name);

                if (project.coverImage != null) {
                    CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
                    circularProgressDrawable.setStrokeWidth(5f);
                    circularProgressDrawable.setCenterRadius(30f);
                    circularProgressDrawable.start();

                    Glide.with(mContext)
                            .load(ApiClient.IMAGE_BASE_URL.concat(project.coverImage))
                            .placeholder(circularProgressDrawable)
                            .into(holder.mProjectImageView);
                }
            }
        }

        @Override
        public int getItemCount() {
            return mProjectList.size() + 1;
        }

        class BaseViewHolder extends RecyclerView.ViewHolder {
            public BaseViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        class ProjectViewHolder extends BaseViewHolder {

            private final View mRootView;
            private final TextView mNameTextView;
            private final ImageView mProjectImageView;

            private Project mProject;

            ProjectViewHolder(View itemView) {
                super(itemView);

                mRootView = itemView;
                mNameTextView = itemView.findViewById(R.id.name_text_view);
                mProjectImageView = itemView.findViewById(R.id.project_image_view);

                mRootView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onClickProject(mProject);
                    }
                });
            }
        }

        class AboutButtonViewHolder extends BaseViewHolder {

            private final View mRootView;
            private final Button mButton;
            private final ImageView mImageView;

            AboutButtonViewHolder(View itemView) {
                super(itemView);

                mRootView = itemView;
                mButton = itemView.findViewById(R.id.info_button);
                mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onClickAboutButton();
                    }
                });
                mImageView = itemView.findViewById(R.id.about_image_view);
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onClickAboutButton();
                    }
                });
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
