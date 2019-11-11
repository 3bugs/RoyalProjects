package th.ac.dusit.dbizcom.royalprojects.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import th.ac.dusit.dbizcom.royalprojects.R;

public class DeveloperFragment extends Fragment {

    private DeveloperFragmentListener mListener;

    public DeveloperFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_developer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DeveloperFragmentListener) {
            mListener = (DeveloperFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement DeveloperFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface DeveloperFragmentListener {
    }
}
