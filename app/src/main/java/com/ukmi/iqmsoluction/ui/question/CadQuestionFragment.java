    package com.ukmi.iqmsoluction.ui.question;

    import android.content.Context;
    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.fragment.app.Fragment;

    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.Toast;

    import com.android.volley.Request;
    import com.android.volley.RequestQueue;
    import com.android.volley.toolbox.JsonArrayRequest;
    import com.android.volley.toolbox.JsonObjectRequest;
    import com.android.volley.toolbox.Volley;
    import com.google.android.material.snackbar.Snackbar;
    import com.ukmi.iqmsoluction.R;
    import com.ukmi.iqmsoluction.model.Question;
    import com.ukmi.iqmsoluction.model.Subject;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;

    public class CadQuestionFragment extends Fragment implements View.OnClickListener {

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
                                        subject.setName(jo.getString("sName"));
                                        subject.setTopic(jo.getString("sTopic"));
                                        subject.setDescription(jo.getString("sDescription"));
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

        private void cadQuestion(@NonNull Question question) throws JSONException {
            JSONObject json = question.toJSONObject();
            json.remove("idquestion");

            JsonObjectRequest jsonObjectReq = new JsonObjectRequest(
                    Request.Method.POST,
                    "http://10.0.2.2/web-service/question/question_insert.php",
                    json,
                    response -> {
                        try {
                            //se a consulta não veio vazia
                            if (response != null) {
                                Context context = requireContext();
                                if (response.getBoolean("success")) {
                                    //limpar campos da tela
                                    this.etQuestion.setText("");
                                    this.etAnswer.setText("");

                                    //primeiro item dos spinners
                                    this.spSubject.setSelection(0);
                                }
                                //mostrando a mensagem que veio do JSON
                                Toast toast = Toast.makeText(
                                        view.getContext(),
                                        response.getString("message"),
                                        Toast.LENGTH_SHORT);
                                toast.show();
                            } else {
                                //mostrar mensagem do response == null
                                Snackbar mensagem = Snackbar.make(
                                        view,
                                        "A consulta não retornou nada!",
                                        Snackbar.LENGTH_LONG);
                                mensagem.show();
                            }
                        } catch (Exception e) {
                            //mostrar mensagem da exception
                            Snackbar mensagem = Snackbar.make(
                                    view,
                                    "Ops!Problema com o arquivo JSON: " + e,
                                    Snackbar.LENGTH_LONG);
                            mensagem.show();
                        }
                    },
                    error -> {
                        //mostrar mensagem que veio do servidor
                        Snackbar mensagem = Snackbar.make(
                                view,
                                "Ops! Houve um problema: " + error.toString(),
                                Snackbar.LENGTH_LONG);
                        mensagem.show();
                    }
            );
            //colocando nova request para fila de execução
            requestQueue.add(jsonObjectReq);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            this.view = inflater.inflate(R.layout.fragment_cad_question, container, false);

            this.spSubject = (Spinner) view.findViewById(R.id.spSubject);

            this.etQuestion = (EditText) view.findViewById(R.id.etQuestion);

            this.etAnswer = (EditText) view.findViewById(R.id.etAnswer);

            this.btnSave = (Button) view.findViewById(R.id.btnSave);

            this.btnSave.setOnClickListener(this);

            this.requestQueue = Volley.newRequestQueue(view.getContext());

            this.requestQueue.start();

            try {
                this.consultSubject();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return this.view;
        }
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnSave) {
                try {
                    //instanciando objeto de negócio
                    Question q = new Question();
                    //populando objeto com dados da tela
                    q.setStatement(this.etQuestion.getText().toString());
                    q.setAnswer(this.etAnswer.getText().toString());

                    int pos = this.spSubject.getSelectedItemPosition();
                    Subject subject = (Subject) this.spSubject.getItemAtPosition(pos);
                    q.setSubject_id(subject.getIdSubject());

                    q.setTopic(" ");
                    q.setTip(" ");

                    try {
                        cadQuestion(q);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                catch (Exception e){
                    Context context = view.getContext();
                    CharSequence text = "erro ao salvar!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

        }
    }