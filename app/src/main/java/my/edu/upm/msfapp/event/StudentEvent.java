package my.edu.upm.msfapp.event;

import java.util.ArrayList;

import my.edu.upm.msfapp.model.Evaluation;
import my.edu.upm.msfapp.model.Question;
import my.edu.upm.msfapp.model.Section;
import my.edu.upm.msfapp.model.User;

/**
 * Created by mohdfadzli on 2/6/2017.
 */

public class StudentEvent {
    public static class UnevaluateUserList {
        private final int listType;
        private ArrayList<User> visitors;

        public UnevaluateUserList(ArrayList<User> visitors, int listType) {
            this.visitors = visitors;
            this.listType = listType;
        }

        public int getListType() {
            return listType;
        }

        public ArrayList<User> getUserList() {
            return visitors;
        }
    }

    public static class UnevaluateUserListFailed {

    }

    public static class OnUpdateEvaluation {

    }



    public static class EvaluationList {
        private ArrayList<Evaluation> visitors;

        public EvaluationList(ArrayList<Evaluation> visitors) {
            this.visitors = visitors;
        }

        public ArrayList<Evaluation> getEvaluationList() {
            return visitors;
        }
    }

    public static class EvaluationListFailed {

    }

    public static class SectionList {
        private ArrayList<Section> visitors;

        public SectionList(ArrayList<Section> visitors) {
            this.visitors = visitors;
        }

        public ArrayList<Section> getSectionList() {
            return visitors;
        }
    }

    public static class SectionListFailed {

    }

    public static class QuestionList {
        private long sectionId;
        private ArrayList<Question> questionList;

        public QuestionList(ArrayList<Question> questionList, long sectionId) {
            this.questionList = questionList;
            this.sectionId=sectionId;
        }

        public long getSectionId() {
            return sectionId;
        }

        public ArrayList<Question> getQuestionList() {
            return questionList;
        }
    }

    public static class QuestionListFailed {

    }
}
