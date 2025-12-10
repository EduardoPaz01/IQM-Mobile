package com.ukmi.iqmsoluction.ui.question;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.ukmi.iqmsoluction.R;
import com.ukmi.iqmsoluction.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 */
public class conQuestionFragment extends Fragment
        implements Response.ErrorListener, Response.Listener<JSONArray> {

    //Lista que vai armazenar os objetos que retornam do Web Service
    private ArrayList<Question> questions;
    //Fila de requests da biblioteca Volley
    private RequestQueue requestQueue;
    //Objeto da biblioteca Volley que faz o request para o Web Service
    private JsonArrayRequest jsonArrayReq;
    //Objeto view que representa a tela utilizado em diversos metodos
    private View view;


    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public conQuestionFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static conQuestionFragment newInstance(int columnCount) {
        conQuestionFragment fragment = new conQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_con_question_list, container, false);

        //instanciando a fila de requests - caso o objeto seja o view
        this.requestQueue = Volley.newRequestQueue(view.getContext());

        //inicializando a fila de requests do SO
        this.requestQueue.start();

        try {
            jsonArrayReq = new JsonArrayRequest(
                    Request.Method.POST,
                    "http://10.0.2.2/web-service/question/question_consult.php",
                    new JSONArray("[{}]"),
                    this, this);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //Inclui a request na fila
        requestQueue.add(jsonArrayReq);

        return this.view;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Snackbar mensagem = Snackbar.make(view,
                "Ops! Houve um problema ao realizar a consulta: " +
                        error.toString(), Snackbar.LENGTH_LONG);
        mensagem.show();
    }

    @Override
    public void onResponse(JSONArray jsonArray) {
        try {
            if (jsonArray != null) {
                Question questao = null;
                this.questions = new ArrayList<Question>();

                for (int pos=0;pos<jsonArray.length(); pos++) {
                    JSONObject jo = jsonArray.getJSONObject(pos);
                    questao = new Question(jo);
                    this.questions.add(questao);
                }

                if (view instanceof RecyclerView) {
                    Context context = view.getContext();
                    RecyclerView recyclerView =
                            (RecyclerView) view;
                    if (mColumnCount <= 1) {
                        recyclerView.setLayoutManager(
                                new LinearLayoutManager(context));
                    } else {
                        recyclerView.setLayoutManager(
                                new GridLayoutManager(context,
                                        mColumnCount));
                    }
                    recyclerView.setAdapter(
                            new QuestionRecyclerViewAdapter(this.questions));
                }
            }else {
                Snackbar mensagem = Snackbar.make(view,
                        "A consulta não retornou nenhum registro!",
                        Snackbar.LENGTH_LONG);
                mensagem.show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}