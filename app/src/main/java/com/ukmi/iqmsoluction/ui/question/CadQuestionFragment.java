package com.ukmi.iqmsoluction.ui.question;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ukmi.iqmsoluction.R;

public class CadQuestionFragment extends Fragment {

    private EditText etQuestion;
    private EditText etAnswer;
    private Spinner spSubject;
    private Button btnSave;
    private RequestQueue requestQueue;


    private View view;

    public CadQuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_cad_question, container, false);

        this.spSubject = (Spinner) view.findViewById(R.id.spSubject);

        this.requestQueue = Volley.newRequestQueue(view.getContext());

        this.requestQueue.start();

        return this.view;
    }
}