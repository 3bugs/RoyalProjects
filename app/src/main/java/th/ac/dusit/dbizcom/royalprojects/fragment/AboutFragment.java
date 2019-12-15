package th.ac.dusit.dbizcom.royalprojects.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import th.ac.dusit.dbizcom.royalprojects.R;

public class AboutFragment extends Fragment {

    private AboutFragmentListener mListener;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView developerImageView = view.findViewById(R.id.developer_image_view);
        developerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickDeveloper();
            }
        });
        ImageView referenceImageView = view.findViewById(R.id.reference_image_view);
        referenceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickReference();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AboutFragmentListener) {
            mListener = (AboutFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AboutFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface AboutFragmentListener {
        void onClickDeveloper();
        void onClickReference();
    }
}
