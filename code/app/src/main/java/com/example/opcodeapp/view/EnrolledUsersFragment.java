package com.example.opcodeapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.opcodeapp.R;
import com.example.opcodeapp.UserArrayAdapter;
import com.example.opcodeapp.databinding.FragmentEnrolledUsersBinding;
import com.example.opcodeapp.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnrolledUsersFragment extends Fragment {

    /**
     * The list of users to be displayed.
     */
    private List<User> dataList;

    /**
     * The ListView for the list of users.
     */
    private ListView userList;

    /**
     * The ArrayAdapter for the list of users.
     */
    private ArrayAdapter<User> userAdapter;


    /**
     * The binding for the fragment.
     */
    private FragmentEnrolledUsersBinding binding;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_enrolled_users, container, false);
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        User[] receivedArray = EnrolledUsersFragmentArgs.fromBundle(getArguments()).getUserList();

        dataList = new ArrayList<User>(Arrays.asList(receivedArray));

        userList = view.getRootView().findViewById(R.id.enrolled_users_list_view);

        userAdapter = new UserArrayAdapter(getContext(), dataList);

        userList.setAdapter(userAdapter);

        userAdapter = new UserArrayAdapter(getContext(), dataList);

        userList.setAdapter(userAdapter);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}
