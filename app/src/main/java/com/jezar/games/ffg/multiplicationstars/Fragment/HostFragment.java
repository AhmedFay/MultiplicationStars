package com.jezar.games.ffg.multiplicationstars.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jezar.games.ffg.multiplicationstars.R;
import com.jezar.games.ffg.multiplicationstars.Utils.UtilsIPs;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple {@link Fragment} subclass.
 */
public class HostFragment extends Fragment {

    ServerSocket echoServer = null;
    Socket clientSocket = null;
    PrintStream output;
    DataInputStream input;

    TextView tvHostLog;
    TextView tvHostStatus;
    TextView tvHostIp;

    public HostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_host, container, false);

        tvHostLog = root.findViewById(R.id.tvHostLog);
        tvHostStatus = root.findViewById(R.id.tvHostStatus);
        tvHostIp = root.findViewById(R.id.tvHostIp);

        try {
            echoServer = new ServerSocket(42124);
            tvHostStatus.setText("status : enable");

            String ip = UtilsIPs.getMACAddress("wlan0");
            UtilsIPs.getMACAddress("eth0");
            UtilsIPs.getIPAddress(true);
            UtilsIPs.getIPAddress(false);

//            WifiManager wm = (WifiManager) getActivity().getApplicationContext().getSystemService(getActivity().WIFI_SERVICE);
//            String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
            tvHostIp.setText("server ip : " + echoServer.getInetAddress().getHostAddress());
            tvHostLog.append("\n- Server Ready ");

        } catch (IOException e) {
            tvHostLog.append("\n- Server Exception:  " + e.getMessage());
            return root;
        }

        try {
            tvHostLog.append("\n- waiting client...");
            while (true) {
                clientSocket = echoServer.accept();

                String cs = "#" + clientSocket + " from "
                        + clientSocket.getInetAddress() + ":"
                        + clientSocket.getPort() + "\n";

//                cs = clientSocket.getInetAddress().getHostAddress();
                tvHostLog.append("\n- Connected with " + cs);
            }
        } catch (Exception e) {
            tvHostLog.append("\n- Client Exception:  " + e.getMessage());
        }

        new Thread() {
            @Override
            public void run() {

                try {
                    output = new PrintStream(clientSocket.getOutputStream());
                    input = new DataInputStream(clientSocket.getInputStream());
                } catch (Exception e) {
                    tvHostLog.append("\n- Stream Exception:  " + e.getMessage());
                }

                try {
                    String line;
                    while (true) {
                        line = input.readLine();
                        tvHostLog.append(line + "\n");
                        output.print(line + "\n");
                    }
                } catch (Exception e) {
                    tvHostLog.append("\n- Send/Receive Exception:  " + e.getMessage());
                }

            }
        }.start();

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            echoServer.close();
        } catch (IOException e) {
        }
    }
}
