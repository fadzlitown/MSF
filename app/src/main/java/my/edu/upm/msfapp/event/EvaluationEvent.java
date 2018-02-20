package my.edu.upm.msfapp.event;

import java.util.ArrayList;

import my.edu.upm.msfapp.model.Response;

/**
 * Created by mohdfadzli on 8/6/2017.
 */

public class EvaluationEvent {
    public static class StartResponse {
        private Response visitors;

        public StartResponse(Response visitors) {
            this.visitors = visitors;
        }

        public Response getStartResponse() {
            return visitors;
        }
    }

    public static class StartResponseFailed {

    }

    public static class SubmitResponse {

        private final long secId;
        boolean status;
        public SubmitResponse(boolean result, long secId) {
            this.status=result;
            this.secId=secId;
        }

        public long getSecId() {
            return secId;
        }

        public boolean getSubmitResponse() {
            return status;
        }
    }

    public static class SubmitResponseFailed {

    }

    public static class EndResponse {
        private final long secId;
        boolean status;
        public EndResponse(boolean result, long secId) {
            this.status=result;
            this.secId=secId;

        }

        public long getSecId() {
            return secId;
        }

        public boolean getEndResponse() {
            return status;
        }
    }

    public static class EndResponseFailed {

    }

    public static class ResponseChoice {
        private ArrayList<my.edu.upm.msfapp.model.ResponseChoice> visitors;


        public ResponseChoice(ArrayList<my.edu.upm.msfapp.model.ResponseChoice> list) {
            this.visitors = list;
        }

        public ArrayList<my.edu.upm.msfapp.model.ResponseChoice> getResponseChoice() {
            return visitors;
        }
    }

    public static class ResponseChoiceFailed {

    }

}
