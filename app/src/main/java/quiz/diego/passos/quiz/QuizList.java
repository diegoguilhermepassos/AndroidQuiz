package quiz.diego.passos.quiz;

import java.util.ArrayList;

public class QuizList {

    ArrayList<QuizEntity> list = new ArrayList<>();
    double maxPoints;

    public QuizList(){
        init();
        calculateMaxPoints();
    }

    void init() {
        //inicio quiz
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.question = "Qual o nome deste campeão";
        quizEntity.img = R.drawable.ic_launcher_background;
        quizEntity.options = new String[]{
                "Zed",
                "Ryze",
                "Fizz",
                "Nunu"
        };
        quizEntity.correctAnswer = 2;
        quizEntity.points = 3;
        quizEntity.img = R.mipmap.ic_launcher;

        list.add(quizEntity);

        //inicio quiz
        quizEntity = new QuizEntity();
        quizEntity.question = "Qual é o nome deste item?";
        quizEntity.img = R.drawable.ic_launcher_background;
        quizEntity.options = new String[]{
                "Eco de Luden",
                "Capuz da Morte De Rabadon",
                "Lâmina Hextec",
                "Cajado do Vazio"
        };
        quizEntity.correctAnswer = 3;
        quizEntity.points = 12;
        quizEntity.img = 0;

        list.add(quizEntity);

        //inicio quiz
        quizEntity = new QuizEntity();
        quizEntity.question = "Qual destes campeões é um assassino:";
        quizEntity.img = R.drawable.ic_launcher_background;
        quizEntity.options = new String[]{
                "Ashe",
                "Kha'zix",
                "Kai'sa",
                "Blitzcrank"
        };
        quizEntity.correctAnswer = 1;
        quizEntity.points = 5;
        quizEntity.img = 0;

        list.add(quizEntity);

        //inicio quiz
        quizEntity = new QuizEntity();
        quizEntity.question = "Qual destes campeões é um assassino:";
        quizEntity.img = R.drawable.ic_launcher_background;
        quizEntity.options = new String[]{
                "Ashe",
                "Kha'zix",
                "Kai'sa",
                "Blitzcrank"
        };
        quizEntity.correctAnswer = 1;
        quizEntity.points = 10;
        quizEntity.img = 0;

        list.add(quizEntity);


    }

    private void calculateMaxPoints() {
        for(QuizEntity entity: list){
            maxPoints = maxPoints + entity.points;
        }
    }

    public ArrayList<QuizEntity> getList(){
        return list;
    }

    public double getMaxPoints() {
        return maxPoints;
    }
}
