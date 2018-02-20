package my.edu.upm.msfapp.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by fadzlirazali on 18/12/2016.
 */

public class Constant {
    public static class Role{
        public static final String R_ROLE ="R_ROLE";
        public static final int STUDENT = 1;
        public static final int LECTURER = 2;
        public static final int NURSE = 3;
    }


    //public enum RoleType : byte { Lecturer = 2, Staff = 3, Patient = 4, Student = 5, }
    @IntDef({RoleType.ALL,  RoleType.LECTURER, RoleType.STAFF, RoleType.PATIENT, RoleType.STUDENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface RoleType {
        public static final int ALL = 1;
        public static final int LECTURER = 2;
        public static final int STAFF = 3;
        public static final int PATIENT = 4;
        public static final int STUDENT = 5;
    }
}
