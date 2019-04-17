package logic;

public class Quiz {
    private int currentIndex;
    private static String[] questions={
            "3, 1, 4, 1, 5",
            "1, 1, 2, 3, 5",
            "1, 4, 9, 16, 25",
            "2, 3, 5, 7, 11",
            "1, 2, 4, 8, 16"
    };

    private static int[] answers={9,8,36,13,32};

    private int score;

    public Quiz(){
        this.currentIndex=0;
        this.score=0;
    }

    public int getScore(){
        return score;
    }

    public boolean hasQuestion(){
        return currentIndex <questions.length;
    }

    public String getQuestion(){
        if(hasQuestion()){
            String q=questions[currentIndex];
            currentIndex++;
            return q;
        }

        return "No Questions";
    }

    public void setAnswer(int answer){
        if(answers[currentIndex-1]==answer) score++;

    }

    public int getTotalScore(){
        return questions.length;
    }
}
