package quiz.diego.passos.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private QuizList quizList = new QuizList();
    private double points;
    RadioGroup radioGroup;
    TextView questionTv;
    ImageView quizImage;
    Button btConfirm;
    boolean quizFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionTv = findViewById(R.id.question_tv);
        btConfirm = findViewById(R.id.confirm_answer_bt);
        radioGroup = findViewById(R.id.answers_rg);
        quizImage = findViewById(R.id.quiz_iv);

        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizFinished){
                    reloadQuiz();
                }else{
                   answerQuiz();
                }
            }
        });
        load();
    }

    void reloadQuiz() {
        quizFinished = false;
        quizList = new QuizList();
        points = 0;
        load();
    }

    void answerQuiz() {
        //validar resposta
        QuizEntity nextQuiz = getQuiz();
        if(nextQuiz!= null) {
            int index = radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId()));
            if(index == nextQuiz.correctAnswer){
                points = points + nextQuiz.points;
            }else{
                Toast.makeText(this, "Xiii... Resposta errada", Toast.LENGTH_LONG).show();
            }
        }
        //removo a resposta atual
        if(!quizList.getList().isEmpty()){
            quizList.getList().remove(0);
        }
        load();
    }

    void enableButton(boolean enable){
        if(enable){
            btConfirm.setEnabled(true);
            btConfirm.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            btConfirm.setTextColor(getResources().getColor(android.R.color.white));
        }else{
            btConfirm.setEnabled(false);
            btConfirm.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            btConfirm.setTextColor(getResources().getColor(android.R.color.black));
        }
    }

    void setQuizImage(int quizImageid) {
        if(quizImageid > 0){
            quizImage.setImageResource(quizImageid);
            quizImage.setVisibility(View.VISIBLE);
        }else{
            quizImage.setVisibility(View.INVISIBLE);
        }
    }

    void load() {
        radioGroup.clearCheck();
        QuizEntity nextQuiz = getQuiz();
        if(nextQuiz!= null){
            btConfirm.setText("Confirmar resposta");
            enableButton(false);
            setQuizImage(nextQuiz.img);
            questionTv.setText(nextQuiz.question);
            radioGroup.setVisibility(View.VISIBLE);

            for(int i = 0; i < nextQuiz.options.length; i++){
                RadioButton child = (RadioButton) radioGroup.getChildAt(i);
                child.setText(nextQuiz.options[i]);
                child.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            enableButton(true);
                        }
                    }
                });
            }
        }else{
            questionTv.setText("Quiz finalizado. Você fêz "+points+" pontos de possíveis "+quizList.getMaxPoints()+" pontos.");
            btConfirm.setText("Novo Quiz");
            radioGroup.setVisibility(View.INVISIBLE);
            enableButton(true);
            quizFinished = true;
            setQuizImage(0);
        }
    }

    private QuizEntity getQuiz() {
        if(quizList.getList().isEmpty()){
            return null;
        }else{
            return quizList.getList().get(0);
        }
    }
}
