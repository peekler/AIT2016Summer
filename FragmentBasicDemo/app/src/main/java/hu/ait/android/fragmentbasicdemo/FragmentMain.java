package hu.ait.android.fragmentbasicdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FragmentMain extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(getContext(),R.layout.fragment_main,null);

        Button btnPress = (Button) rootView.findViewById(R.id.btnPress);
        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Event from Fragment", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}
