package my.edu.upm.msfapp.ui.main.evaluate.evaluation;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import my.edu.upm.msfapp.MyApplication;
import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.event.EvaluationEvent;
import my.edu.upm.msfapp.event.StudentEvent;
import my.edu.upm.msfapp.job.EndEvaluationJob;
import my.edu.upm.msfapp.job.GetAnswerChoiceJob;
import my.edu.upm.msfapp.job.GetQuestionsJob;
import my.edu.upm.msfapp.job.SubmitEvaluationJob;
import my.edu.upm.msfapp.model.Question;
import my.edu.upm.msfapp.model.Response;
import my.edu.upm.msfapp.model.ResponseChoice;
import my.edu.upm.msfapp.model.Submit;
import my.edu.upm.msfapp.util.Util;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionFragment extends BaseSectionFragment {

    private static final String SEC_ID = "sec_id";
    private static final String EVAL_ID = "eval_id";

    private static final String SEC_LAST_PAGE = "last_page";
    private static String RESPONSE_ID = "response_id";
    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;
    Unbinder unbinder;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.progress)
    FrameLayout progress;
    @BindView(R.id.no_data)
    FrameLayout noData;
    @BindView(R.id.submit)
    Button submit;
    private long secId;
    private boolean isLastPage = false;
    private boolean hasMissingAnwser = false;
    private ArrayAdapter<String> spinnerAdapter;
    private ArrayList<Question> questionList;
    private ArrayList<ResponseChoice> anwserList;
    private long responseId;
    private HashMap<Integer, Long> spinnerHashMap;
    private HashMap<Integer, String> commentHashMap;
    private ArrayList<Submit> submitArrayList;
    private static int count = 0;
    private long evalId;

    public static Fragment newInstance(long evalId, long secId, long responseId, boolean isLastPage) {
        Bundle args = new Bundle();
        args.putLong(EVAL_ID, evalId);
        args.putLong(SEC_ID, secId);
        args.putLong(RESPONSE_ID, responseId);
        args.putBoolean(SEC_LAST_PAGE, isLastPage);
        Fragment fragment = new SectionFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(SEC_ID)) {
            evalId = getArguments().getLong(EVAL_ID);
            secId = getArguments().getLong(SEC_ID);
            responseId = getArguments().getLong(RESPONSE_ID);
            isLastPage = getArguments().getBoolean(SEC_LAST_PAGE);
            MyApplication.addJobInBackground(new GetQuestionsJob(0, 20, secId));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_section, container, false);
        unbinder = ButterKnife.bind(this, v);
        submitArrayList = new ArrayList<Submit>();
        setListShown(false);

        //check is first time
        if (isLastPage) {
            next.setVisibility(View.GONE);
            submit.setVisibility(View.VISIBLE);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        new AlertDialog.Builder(getContext()).setTitle("Are you sure want to submit?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        updateSubmit();
                                        //SubmitHashMap Conversion into ArrayList
                                        ArrayList<Submit> arr = ((EvaluationActivity) getActivity()).convertSubmitMapToArrayList();
                                        //Submit Answer API
                                        setListShown(false);
                                        MyApplication.addJobInBackground(new SubmitEvaluationJob(arr,secId));
                                        //End API
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
//                    }
                }
            });
        } else {
            submit.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
            next.setText("Next");
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curPage = ((EvaluationActivity) getActivity()).viewPager.getCurrentItem();
                    ((EvaluationActivity) getActivity()).viewPager.setCurrentItem(curPage + 1);
                    updateSubmit();

                }
            });
        }
        return v;
    }

    private void setListShown(boolean shown) {
        Util.setListShown(scrollView, progress, shown, true);
    }


    public void updateSubmit() {
        if (questionList != null && questionList.size() > 0) {
            for (int i = 0; i < questionList.size(); i++) {
                Submit submit = new Submit();
                Question q = new Question();
                q.setQuestionId(questionList.get(i).getQuestionId());
                submit.setQuestion(q);
                ResponseChoice rc = new ResponseChoice();
                rc.setResponseChoiceId(spinnerHashMap.get(i));
                submit.setResponseChoice(rc);
                Response r = new Response();
                r.setResponseId(responseId);
                submit.setResponse(r);
                if (commentHashMap.get(i) != null) {
                    submit.setComment(commentHashMap.get(i));
                } else {
                    submit.setComment("");
                }

                //update
                ((EvaluationActivity) getActivity()).addSubmitMap((int) questionList.get(i).getQuestionId(), submit);
            }
        }
        Log.d("SUBMIT", "onClick: " + new Gson().toJson(((EvaluationActivity) getActivity()).getSubmitHashMap()));
    }

    /**
     * QuestionList Job Callback
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(StudentEvent.QuestionList event) {
        setListShown(true);
        if (event.getQuestionList().size() > 0 && secId == event.getSectionId()) {
            questionList = new ArrayList<Question>();
            Collections.reverse(event.getQuestionList());
            questionList.addAll(event.getQuestionList());
            MyApplication.addJobInBackground(new GetAnswerChoiceJob(0, 20, evalId));
        }
    }

    public void addRowQuestionView(final int no, String title, String[] answerStr) {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout.setPadding(15, 10, 15, 10);

        TextView titleView = new TextView(getActivity());
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titleView.setLayoutParams(lparams);
        titleView.setText(no + 1 + ". " + title);
        titleView.setTextSize(16);
        titleView.setPadding(0, 10, 0, 0);
        layout.addView(titleView);

        //reverse
        final Spinner spinnerAnwser = new Spinner(getActivity());
        spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, answerStr);
        spinnerAnwser.setAdapter(spinnerAdapter);
        spinnerAnwser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    hasMissingAnwser = true;
                }
                spinnerHashMap.put(no, anwserList.get(position).getResponseChoiceId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinnerHashMap.put(no, anwserList.get(0).getResponseChoiceId()); //by def set into first index
            }
        });
        LinearLayout.LayoutParams lparams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        spinnerAnwser.setLayoutParams(lparams2);
        spinnerAnwser.setPadding(0, 0, 0, 3);
        layout.addView(spinnerAnwser);

        //comment
        EditText et = new EditText(getActivity());
        LinearLayout.LayoutParams lparams3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(lparams3);
        et.setTextSize(16);
        et.setPadding(10, 0, 0, 35);
        et.setHint("Comment");
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(150)});
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.toString().length() > 1) {
                    commentHashMap.put(no, s.toString());
                }
            }
        });
        layout.addView(et);

//        View line = new View(getActivity());
//        line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));
//        line.setBackgroundColor(Color.parseColor("#B3B3B3"));
//        layout.addView(line);
        mainLayout.addView(layout);
    }

    /**
     * Get AnswerChoice Job Callback
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(EvaluationEvent.ResponseChoice event) {
        String[] answerStr = new String[event.getResponseChoice().size()];
        if (event.getResponseChoice().size() > 0 && questionList.size() != mainLayout.getChildCount()) {
            anwserList = new ArrayList<ResponseChoice>();
            anwserList.addAll(event.getResponseChoice());
            for (int i = 0; i < event.getResponseChoice().size(); i++) {
                answerStr[i] = event.getResponseChoice().get(i).getText();
            }

            spinnerHashMap = new HashMap<>();
            commentHashMap = new HashMap<>();
            if (questionList.size() > 0) {
                noData.setVisibility(View.GONE);
                for (int i = 0; i < questionList.size(); i++) {
                    Question question = questionList.get(i);
                    addRowQuestionView(i, question.getText(), answerStr);
                }
            } else {
                noData.setVisibility(View.VISIBLE);
            }
//            getArguments().remove(SEC_ID);
        }
    }


    /**
     * Submit & End Evaluation Job Callback
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onHandle(EvaluationEvent.SubmitResponse event) {
        setListShown(true);
        if (event.getSubmitResponse() && secId==event.getSecId())
            MyApplication.addJobInBackground(new EndEvaluationJob((int) responseId,secId));

        EventBus.getDefault().removeStickyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(EvaluationEvent.EndResponse event) {
        setListShown(true);
        if (event.getEndResponse() && secId==event.getSecId()) {
            Toast.makeText(getActivity(), "Successfully evaluated!", Toast.LENGTH_SHORT).show();
            getActivity().finish();
            EventBus.getDefault().postSticky(new StudentEvent.OnUpdateEvaluation());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(EvaluationEvent.SubmitResponseFailed event) {
        setListShown(true);
        Toast.makeText(getContext(), "Sorry, can't submit the evaluation", Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHandle(EvaluationEvent.EndResponseFailed event) {
        setListShown(true);
        Toast.makeText(getContext(), "Sorry, can't end the evaluation", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
