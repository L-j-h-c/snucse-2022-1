package attendancechecker;

import attendancechecker.attendance.AttendanceList;
import attendancechecker.attendance.Student;
import attendancechecker.attendance.Lecture;

import attendancechecker.utils.Pair;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Grader {

    public long logsToMinutes(String startLog, String endLog){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        long minutes = 0;

        try {
            Date startTime = formatter.parse(startLog);
            Date endTime = formatter.parse(endLog);

            minutes = (endTime.getTime() - startTime.getTime());
        } catch (Exception e){
            e.printStackTrace();
        }

        return minutes;
    }

    public Map<String,Map<String, Double>> gradeSimple(AttendanceList attendanceList, String attendanceDirPath) {
        // TODO Problem 1-1
        Map<String, Map<String, Double>> attendance = new HashMap<>();

        return attendance;
    }


    public Map<String,Map<String, Double>> gradeRobust(AttendanceList attendanceList, String attendanceDirPath) {
        // TODO Problem 1-2

        return attendance;
    }

}

