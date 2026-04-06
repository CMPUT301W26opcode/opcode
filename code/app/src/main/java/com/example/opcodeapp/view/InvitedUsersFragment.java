package com.example.opcodeapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.opcodeapp.R;
import com.example.opcodeapp.adapter.InvitedUserArrayAdapter;
import com.example.opcodeapp.callback.FirestoreCallbackApplicantsReceive;
import com.example.opcodeapp.callback.FirestoreCallbackSend;
import com.example.opcodeapp.databinding.FragmentInvitedUsersBinding;
import com.example.opcodeapp.enums.ApplicantStatus;
import com.example.opcodeapp.model.Applicant;
import com.example.opcodeapp.model.Event;
import com.example.opcodeapp.model.Notification;
import com.example.opcodeapp.repository.ApplicantRepository;
import com.example.opcodeapp.repository.NotificationRepository;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


/**
 * The fragment for the list of users who applied for the event and were invited.
 */
public class InvitedUsersFragment extends Fragment implements DeclinedUserDialogFragment.DeclinedUserDialogListener {

    /**
     * The list of users to be displayed.
     */
    private ArrayList<Applicant> dataList = new ArrayList<>();

    /**
     * The ListView for the list of users.
     */
    private ListView userList;

    /**
     * The ArrayAdapter for the list of users.
     */
    private ArrayAdapter<Applicant> userAdapter;
    private ApplicantRepository applicantRepository;
    private Event event;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invited_users, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        event = getArguments().getParcelable("event", Event.class);
        applicantRepository = new ApplicantRepository(FirebaseFirestore.getInstance());
        userList = view.findViewById(R.id.invited_users_list_view);

        applicantRepository.fetchApplicantsByEvent(event.getId(), new FirestoreCallbackApplicantsReceive() {
            @Override
            public void onDataReceived(List<Applicant> applicants) {
                for (Applicant applicant : applicants) {
                    ApplicantStatus status = applicant.getStatus();
                    if (status == ApplicantStatus.INVITED || status == ApplicantStatus.DECLINED || status == ApplicantStatus.ACCEPTED) {
                        dataList.add(applicant);
                    }
                }
                userAdapter = new InvitedUserArrayAdapter(getContext(), dataList);
                userList.setAdapter(userAdapter);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getContext(), "Error fetching applicants", Toast.LENGTH_SHORT).show();
            }
        });

        Button message_button = view.findViewById(R.id.accepted_msg_send_btn);
        message_button.setOnClickListener(v -> {
            sendMessages(v, ApplicantStatus.ACCEPTED, R.id.accepted_msg_input);
        });

        Button decline_button = view.findViewById(R.id.declined_msg_send_btn);
        decline_button.setOnClickListener(v -> {
            sendMessages(v, ApplicantStatus.DECLINED, R.id.declined_msg_input);
        });

        /**
         * Click Listener for each of the users in the listview. If the user has declined the invitation, they can be removed from the list.
         */
        userList.setOnItemClickListener((parent, view1, position, id) -> {
            Applicant applicant = userAdapter.getItem(position);
            if (applicant != null && applicant.getStatus() == ApplicantStatus.DECLINED) {
                DeclinedUserDialogFragment fragment = DeclinedUserDialogFragment.newInstance(applicant, event);
                fragment.show(getChildFragmentManager(), "Remove");
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * Removes a user from the list of invited users.
     *
     * @param applicant The user that has declined the invitation.
     * @param event     The event.
     */
    @Override
    public void removeUser(Applicant applicant, Event event) {
        applicant.setStatus(ApplicantStatus.DECLINED_REMOVED);
        applicantRepository.updateApplicant(applicant, new FirestoreCallbackSend() {
            @Override
            public void onSendSuccess(Void aVoid) {
                Toast.makeText(getContext(), "User removed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSendFailure(Exception e) {
                Toast.makeText(getContext(), "Error removing user", Toast.LENGTH_SHORT).show();
            }
        });

        dataList.remove(applicant);
        userAdapter.notifyDataSetChanged();
    }

    private void sendMessages(View v, ApplicantStatus status, int input) {
        NotificationRepository repo = new NotificationRepository(FirebaseFirestore.getInstance());
        ApplicantRepository repository = new ApplicantRepository(FirebaseFirestore.getInstance());
        repository.fetchApplicantsByStatus(event, status, new FirestoreCallbackApplicantsReceive() {
            @Override
            public void onDataReceived(List<Applicant> applicants) {
                EditText text = getView().findViewById(input);
                String message = text.getText().toString();
                Log.d("NotificationMessage", "Sending message: " + message);
                applicants.forEach(applicant -> {
                    repo.addNotification(
                            new Notification(applicant.getUserId(), message, event.getId(), "event_detail"), new FirestoreCallbackSend() {
                                @Override
                                public void onSendSuccess(Void unused) {
                                    Log.i("NotificationMessage", "notification message sent " + status);
                                }

                                @Override
                                public void onSendFailure(Exception e) {
                                    Log.i("NotificationMessage", "notification couldn't be sent " + status, e);
                                }
                            }
                    );
                });
            }

            @Override
            public void onError(Exception e) {
                Log.e("NotificationMessage", "can't send notification to " + status, e);
            }
        });
    }
}
