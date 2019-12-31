package com.jezar.games.ffg.multiplicationstars.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jezar.games.ffg.multiplicationstars.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinFragment extends Fragment {

    Socket clientSocket;
    TextView tvJoinLog;
    EditText etHostIp;

    static DataOutputStream os;
    static DataInputStream is;

    public JoinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_join, container, false);

        tvJoinLog = root.findViewById(R.id.tvJoinLog);
        etHostIp = root.findViewById(R.id.etHostIp);
        Button btnJoinServerHost = root.findViewById(R.id.btnJoinServerHost);

        btnJoinServerHost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String ip = etHostIp.getText().toString();
                    clientSocket = new Socket(ip, 42124);

                    tvJoinLog.append("\n- connect success");
                } catch (IOException e) {
                    tvJoinLog.append("\n- connect Exception:  " + e.getMessage());
                }

                try {
                    os = new DataOutputStream(clientSocket.getOutputStream());
                    is = new DataInputStream(clientSocket.getInputStream());

                    tvJoinLog.append("\n- get stream success");
                } catch (Exception e) {
                    tvJoinLog.append("\n- stream Exception:  " + e.getMessage());
                }
            }
        });


        return root;
    }

}
