package th.ac.dusit.dbizcom.royalprojects;

import android.os.Bundle;

import th.ac.dusit.dbizcom.royalprojects.fragment.AboutFragment;
import th.ac.dusit.dbizcom.royalprojects.fragment.DeveloperFragment;
import th.ac.dusit.dbizcom.royalprojects.fragment.InfoFragment;
import th.ac.dusit.dbizcom.royalprojects.fragment.ProjectDetailsFragment;
import th.ac.dusit.dbizcom.royalprojects.fragment.ProjectListFragment;
import th.ac.dusit.dbizcom.royalprojects.model.Project;

public class MainActivity extends BaseActivity implements
        ProjectListFragment.ProjectListFragmentListener,
        ProjectDetailsFragment.ProjectDetailsFragmentListener,
        InfoFragment.InfoFragmentListener,
        AboutFragment.AboutFragmentListener,
        DeveloperFragment.DeveloperFragmentListener {

    private static final String TAG_PROJECT_LIST_FRAGMENT = "project_list_fragment";
    private static final String TAG_PROJECT_DETAILS_FRAGMENT = "project_details_fragment";
    private static final String TAG_INFO_FRAGMENT = "info_fragment";
    private static final String TAG_DEVELOPER_FRAGMENT = "developer_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new ProjectListFragment(), TAG_PROJECT_LIST_FRAGMENT, false, FragmentTransitionType.NONE);
    }

    @Override
    public void onClickProject(Project project) {
        loadFragment(ProjectDetailsFragment.newInstance(project), TAG_PROJECT_DETAILS_FRAGMENT, true, FragmentTransitionType.SLIDE);
    }

    @Override
    public void onClickAboutButton() {
        loadFragment(new AboutFragment(), TAG_DEVELOPER_FRAGMENT, true, FragmentTransitionType.SLIDE);
    }

    @Override
    public void onClickDeveloper() {
        loadFragment(new DeveloperFragment(), TAG_DEVELOPER_FRAGMENT, true, FragmentTransitionType.SLIDE);
    }

    @Override
    public void onClickInfoButton(int projectId) {
        loadFragment(InfoFragment.newInstance(projectId), TAG_INFO_FRAGMENT, true, FragmentTransitionType.SLIDE);
    }
}
