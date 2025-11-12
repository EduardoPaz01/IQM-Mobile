package com.ukmi.iqmsoluction.ui.question;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ukmi.iqmsoluction.R;

public class CadQuestionFragment extends Fragment {

    private EditText etQuestion;
    private EditText etAnswer;
    private Spinner spSubject;
    private Button btnSave;

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
        return inflater.inflate(R.layout.fragment_cad_question, container, false);
    }
}