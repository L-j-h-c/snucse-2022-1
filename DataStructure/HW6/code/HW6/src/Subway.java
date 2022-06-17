import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Subway
{
    final Long Infinity = Long.MAX_VALUE;

    static Hashtable<String, String> idToName = new Hashtable<>();
    static Hashtable<String, ArrayList<Station>> stations = new Hashtable<>();
    public static void main(String[] args)
    {

        // 역에 대한 정보를 저장하는 부분
        String fileName = args[0];
        File file = new File(fileName);
        System.out.println(file.getPath());
        boolean informationChanged = false;
        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if(line.equals("")) {
                    informationChanged = true;
                    line = scanner.nextLine();
                }
                if(!informationChanged) {
                    // 정보 저장하기
                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    String id = stringTokenizer.nextToken();
                    String name = stringTokenizer.nextToken();
                    String lineNum = stringTokenizer.nextToken();
//                    String idWithLine = id + lineNum;

                    Station station = new Station(id, name, lineNum);

                    idToName.put(id,name);

                    if(stations.containsKey(name)) {
                        ArrayList<Station> prevStations = stations.get(name);
                        // 동일한 라인에 동일한 이름일 경우 edge를 부여하지 않음.
                        boolean sameLine = false;
                        for(Station s : prevStations) {
                            if((s.lineNum.equals(lineNum)) || (sameLine)) {
                                sameLine = true;
                            }
                        }
                        if(!sameLine) {
                            for(Station s : prevStations) {
                                s.edges.add(new Edge(s, station, 5L));
                                station.edges.add(new Edge(station, s, 5L));
                            }
                            stations.get(name).add(station);
                        }
                    } else {
                        ArrayList<Station> arr = new ArrayList<>();
                        arr.add(station);
                        stations.put(name,arr);
                    }
                } // 아래는 edge 할당하는 부분
                else {
                    // 정보 저장하기
                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    String beginId = stringTokenizer.nextToken();
                    String destinationId = stringTokenizer.nextToken();

                    String beginName = idToName.get(beginId);
                    String destinationName = idToName.get(destinationId);

                    Long time = Long.parseLong(stringTokenizer.nextToken());

                    for(Station b : stations.get(beginName)) {
                        for(Station d : stations.get(destinationName)) {
                            if(b.lineNum.equals(d.lineNum)) {
                                b.edges.add(new Edge(b, d, time));
                            }
                        }
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<String> s = stations.keySet();
        for(String name : s) {
            for(Station ar : stations.get(name)) {
                System.out.println(ar);
                for(Edge e : ar.edges) {
                    System.out.println(e.begin +" "+ e.destination +" "+ e.weight);
                }
            }
        }

        // 쿼리를 받아서 최소 시간을 출력하는 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        while (true)
        {
            try
            {
                String input = br.readLine();
                if (input.compareTo("QUIT") == 0)
                    break;

                makePath(input);
            }
            catch (IOException e)
            {
                System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
            }
        }
    }

    private static void makePath(String input)
    {
        String beginSt = input.substring(2);
        String destinationSt = input.substring(2);
    private static Pair closestStationPair(Set<Station> completed, Map<Station, Boolean> completedCheck) {

        Station closestStation = new Station(null, null, null);
        Station beginStation = new Station(null, null, null);

        Long minWeight=Long.MAX_VALUE;

        for(Station s : completed) {
            for(Edge e : s.edges) {
                if(!completedCheck.get(e.destination)) {
                    if(e.weight<minWeight) {
                        beginStation = e.begin;
                        closestStation = e.destination;
                        minWeight = e.weight;
                    }
                }
            }
        }

        return new Pair(beginStation,closestStation,minWeight);
    }
}