package th.ac.dusit.dbizcom.royalprojects.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import th.ac.dusit.dbizcom.royalprojects.R;
import th.ac.dusit.dbizcom.royalprojects.model.InfoMarker;

public class InfoDetailsFragment extends Fragment {

    private static final String ARG_INFO_DETAILS_JSON = "info_details_json";

    private InfoMarker mInfoMarker;

    private InfoDetailsFragmentListener mListener;

    public InfoDetailsFragment() {
        // Required empty public constructor
    }

    public static InfoDetailsFragment newInstance(InfoMarker infoMarker) {
        InfoDetailsFragment fragment = new InfoDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_INFO_DETAILS_JSON, new Gson().toJson(infoMarker));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String infoDetailsJson = getArguments().getString(ARG_INFO_DETAILS_JSON);
            mInfoMarker = new Gson().fromJson(infoDetailsJson, InfoMarker.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView titleTextView = view.findViewById(R.id.title_text_view);
        TextView detailsTextView = view.findViewById(R.id.details_text_view);
        ImageView imageView = view.findViewById(R.id.image_view);

        titleTextView.setText(mInfoMarker.name);
        detailsTextView.setText(mInfoMarker.details);

        if (getActivity() != null) {
            if (mInfoMarker.imageFileName != null
                    && !("".equals(mInfoMarker.imageFileName))) {
                AssetManager am = getActivity().getAssets();
                try {
                    InputStream stream = am.open(mInfoMarker.imageFileName);
                    Drawable drawable = Drawable.createFromStream(stream, null);
                    imageView.setImageDrawable(drawable);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InfoDetailsFragmentListener) {
            mListener = (InfoDetailsFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InfoDetailsFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface InfoDetailsFragmentListener {
    }
}
