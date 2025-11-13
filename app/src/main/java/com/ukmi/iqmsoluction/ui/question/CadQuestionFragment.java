package com.ukmi.iqmsoluction.ui.question;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.ukmi.iqmsoluction.R;
import com.ukmi.iqmsoluction.model.Subject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CadQuestionFragment extends Fragment {

    private EditText etQuestion;
    private EditText etAnswer;
    private Spinner spSubject;
    private Button btnSave;
    private RequestQueue requestQueue;
    private View view;

    private void consultSubject() throws JSONException {
        JsonArrayRequest jsonArrayReq = null;
        try {
            jsonArrayReq = new JsonArrayRequest(
                    Request.Method.POST,
                    "http://10.0.2.2/web-service/subject/subject_consult.php",
                    new JSONArray("[{}]"),
                    response -> {
                        try {
                            if (response != null) {
                                ArrayList<Subject> listSubjects = new ArrayList<>();
                                for (int pos = 0;pos < response.length();pos++){
                                    JSONObject jo = response.getJSONObject(pos);
                                    Subject subject = new Subject();
                                    subject.setIdSubject(jo.getInt("idsubject"));
                                    subject.setName(jo.getString("name"));
                                    subject.setTopic(jo.getString("topic"));
                                    subject.setDescription(jo.getString("description"));
                                    listSubjects.add(pos, subject);
                                }
                                ArrayAdapter<Subject> adapter = new ArrayAdapter<>(
                                        requireContext(),
                                        android.R.layout.simple_spinner_dropdown_item,
                                        listSubjects);

                                this.spSubject.setAdapter(adapter);
                            } else {
                                Snackbar message = Snackbar.make(view,
                                        "The query returned no records!",
                                        Snackbar.LENGTH_LONG);
                                message.show();
                            }
                        } catch (JSONException e) {
                            Snackbar message = Snackbar.make(view,
                                    "Oops! Problem with the JSON file: " + e,
                                    Snackbar.LENGTH_LONG);
                            message.show();
                        }
                    },
                    error -> {
                        Snackbar message = Snackbar.make(view,
                                "Oops! There was a problem performing the query: " +
                                        error.toString(), Snackbar.LENGTH_LONG);
                        message.show();
                    }
            );
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        requestQueue.add(jsonArrayReq);
    }

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

        try {
            this.consultSubject();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return this.view;
    }
}