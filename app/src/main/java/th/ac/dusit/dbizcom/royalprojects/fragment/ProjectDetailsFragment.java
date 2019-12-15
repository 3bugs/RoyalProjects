package th.ac.dusit.dbizcom.royalprojects.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.asura.library.posters.Poster;
import com.asura.library.posters.RemoteImage;
import com.asura.library.posters.RemoteVideo;
import com.asura.library.views.PosterSlider;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import th.ac.dusit.dbizcom.royalprojects.R;
import th.ac.dusit.dbizcom.royalprojects.model.Project;
import th.ac.dusit.dbizcom.royalprojects.net.ApiClient;

public class ProjectDetailsFragment extends Fragment {

    private static final String ARG_PROJECT_JSON = "project_json";

    private Project mProject;

    private ProjectDetailsFragmentListener mListener;

    public ProjectDetailsFragment() {
        // Required empty public constructor
    }

    public static ProjectDetailsFragment newInstance(Project project) {
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PROJECT_JSON, new Gson().toJson(project));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String projectJson = getArguments().getString(ARG_PROJECT_JSON);
            mProject = new Gson().fromJson(projectJson, Project.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView projectNameTextView = view.findViewById(R.id.project_name_text_view);
        projectNameTextView.setText(mProject.name);

        ImageView projectImageView = view.findViewById(R.id.project_image_view);

        if (mProject.coverImage != null) {
            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(view.getContext());
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            Glide.with(view.getContext())
                    .load(ApiClient.IMAGE_BASE_URL.concat(mProject.coverImage))
                    .placeholder(circularProgressDrawable)
                    .into(projectImageView);
        }

        TextView detailsTextView = view.findViewById(R.id.details_text_view);
        detailsTextView.setText(mProject.details);

        ImageView infoImageView = view.findViewById(R.id.info_image_view);
        infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClickInfoButton(mProject.id);
                }
            }
        });

        Button directionButton = view.findViewById(R.id.direction_button);
        directionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Uri intentUri = Uri.parse("geo:" + mProject.latitude + "," + mProject.longitude *//*+ "?q=" + (mPlace != null ? mPlace.name : mOtop.name)*//*);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, intentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);*/

                String url = String.format(
                        Locale.getDefault(),
                        "http://maps.google.com/maps?daddr=%f,%f",
                        mProject.latitude,
                        mProject.longitude
                );
                Intent intent = new Intent(
                        android.content.Intent.ACTION_VIEW,
                        Uri.parse(url)
                );
                startActivity(intent);
            }
        });

        //setupImageSlider(view);
        setupPosterSlider(view);
    }

    private void setupPosterSlider(View view) {
        PosterSlider posterSlider = view.findViewById(R.id.poster_slider);
        List<Poster> posters = new ArrayList<>();

        //add poster using remote url
        //posters.add(new RemoteImage("http://5911011802043.msci.dusit.ac.th/royal_projects/images/cover_01.jpg"));
        //posters.add(new RemoteImage("http://5911011802043.msci.dusit.ac.th/royal_projects/images/cover_02.jpg"));
        //posters.add(new RemoteImage("http://5911011802043.msci.dusit.ac.th/royal_projects/images/cover_03.jpg"));
        //posters.add(new RemoteImage("http://5911011802043.msci.dusit.ac.th/royal_projects/images/cover_04.jpg"));
        //posters.add(new RemoteImage("http://5911011802043.msci.dusit.ac.th/royal_projects/images/cover_05.jpg"));

        for (String imageFile : mProject.galleryImages) {
            posters.add(new RemoteImage(ApiClient.GALLERY_BASE_URL.concat(imageFile)));
        }

        //add poster using resource drawable
        //posters.add(new DrawableImager(R.drawable.yourDrawable));

        //add raw video using raw resource file
        //posters.add(new RawVideo(R.raw.yourRawFile));

        //add remote video using uri
        String videoFile = null;
        switch (mProject.id) {
            case 1:
                videoFile = "1.mp4";
                break;
            case 2:
                videoFile = "2.mp4";
                break;
            case 3:
                videoFile = "3.mp4";
                break;
            case 4:
                videoFile = "4.mp4";
                break;
            case 5:
                videoFile = "5.mp4";
                break;
        }
        if (videoFile != null) {
            posters.add(new RemoteVideo(Uri.parse("http://5911011802043.msci.dusit.ac.th/royal_projects/video/".concat(videoFile))));
        }

        posterSlider.setPosters(posters);
    }

    /*private void setupImageSlider(View view) {
        SliderLayout mSlider = view.findViewById(R.id.slider);

        ArrayList<String> listUrl = new ArrayList<>();
        //listUrl.add("http://5911011802058.msci.dusit.ac.th/chainat_tourism/images/โฆษณา.png");
        //listUrl.add("http://5911011802058.msci.dusit.ac.th/chainat_tourism/images/สวนนก.png");
        //listUrl.add("http://5911011802058.msci.dusit.ac.th/chainat_tourism/images/สวนส้มโอ.png");

        RequestOptions requestOptions = new RequestOptions().fitCenter();

        //.diskCacheStrategy(DiskCacheStrategy.NONE)
        //.placeholder(R.drawable.placeholder)
        //.error(R.drawable.placeholder);

        for (int i = 0; i < listUrl.size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getActivity());
            sliderView
                    .image(listUrl.get(i))
                    .setRequestOption(requestOptions)
                    //.setBackgroundColor(Color.WHITE)
                    .setProgressBarVisible(true)
                    .setOnSliderClickListener(null);

            //add your extra information
            sliderView.bundle(new Bundle());
            //sliderView.getBundle().putString("extra", listName.get(i));
            mSlider.addSlider(sliderView);
        }

        // set Slider Transition Animation
        // mSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);

        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(3000);
        mSlider.addOnPageChangeListener(null);
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProjectDetailsFragmentListener) {
            mListener = (ProjectDetailsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ProjectDetailsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ProjectDetailsFragmentListener {
        void onClickInfoButton(int projectId);
    }
}
