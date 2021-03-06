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

    // 로그를 받아서 시간 형식으로 변환하고, minutes를 계산해서 return한다.
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

    // 학번 - 강의 - 출석점수로 매핑된 attendance를 return한다.
    // 10%보다 작으면 0점 / 70%보다 작으면 0.5점 / 나머지 1점
    // attendanceDirPath(로그가 있는 경로)와, attendanceList(출석부, 학생의 명단)를 받아서 attendance를 반환한다.
    public Map<String,Map<String, Double>> gradeSimple(AttendanceList attendanceList, String attendanceDirPath) {
        // TODO Problem 1-1
        Map<String, Map<String, Double>> attendance = new HashMap<>();

        for(Student s : attendanceList.students) {

            HashMap lectureScore = new HashMap();

            for(Lecture l : attendanceList.lectures) {

                File file = new File(attendanceDirPath + "/" + s.id + "/" + l.id + "/log0.txt");

                long sumOfTime = 0;
                    String startTime, endTime;

                    try {
                        Scanner scanner = new Scanner(file);
                        while(scanner.hasNext()) {
                            scanner.nextLine();
                            startTime = scanner.nextLine();
                            scanner.nextLine();
                            endTime = scanner.nextLine();
                            sumOfTime += logsToMinutes(startTime,endTime);
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }

                    double lectureStandard = logsToMinutes(l.lectureStart, l.lectureEnd);
                    double ratio = sumOfTime / lectureStandard;

                    if(ratio < 0.1) {
                        lectureScore.put(l.id, 0.0);
                    } else if (ratio < 0.7) {
                        lectureScore.put(l.id, 0.5);
                    } else {
                        lectureScore.put(l.id, 1.0);
                    }
            }
            attendance.put(s.id, lectureScore);
        }
        return attendance;
    }


    public Map<String,Map<String, Double>> gradeRobust(AttendanceList attendanceList, String attendanceDirPath) {
        // TODO Problem 1-2
        Map<String, Map<String, Double>> attendance = new HashMap<>();

        for(Student s : attendanceList.students) {
            HashMap lectureScore = new HashMap();
            for(Lecture l : attendanceList.lectures) {

                File[] files = directoryFiles(attendanceDirPath + "/" + s.id + "/" + l.id);

                long sumOfTime = 0;
                String startTime, endTime;
                boolean admitFlag = false;
                boolean refusedFlag = false;

                for(File f : files) {
                    try {
                        Scanner scanner = new Scanner(f);
                        while(scanner.hasNext()) {
                            String first = scanner.nextLine();
                            if(first.equals("Admitted")) {
                                admitFlag = true;
                                break;
                            } else if(first.equals("Refused")) {
                                refusedFlag = true;
                                break;
                            }
                            startTime = scanner.nextLine();
                            scanner.nextLine();
                            endTime = scanner.nextLine();

                            // 먼저 시작하거나 늦게 끝낸 경우 맞춰줌
                            if(logsToMinutes(startTime, l.lectureStart)>0) {
                                startTime = l.lectureStart;
                            } else if (logsToMinutes(endTime, l.lectureEnd)<0) {
                                endTime = l.lectureEnd;
                            }

                            sumOfTime += logsToMinutes(startTime,endTime);
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }

                double lectureStandard = logsToMinutes(l.lectureStart, l.lectureEnd);
                double ratio = sumOfTime / lectureStandard;

                if (admitFlag) {
                    lectureScore.put(l.id, 1.0);
                } else if(refusedFlag) {
                    lectureScore.put(l.id, 0.0);
                } else if (ratio < 0.1) {
                    lectureScore.put(l.id, 0.0);
                } else if (ratio < 0.7) {
                    lectureScore.put(l.id, 0.5);
                } else {
                    lectureScore.put(l.id, 1.0);
                }
            }
            attendance.put(s.id, lectureScore);
        }
        return attendance;
    }


    private File[] directoryFiles(String directoryName) {
        File directory = new File(directoryName);
        return directory.listFiles();
    }
}

