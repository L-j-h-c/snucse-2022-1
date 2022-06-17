import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Subway
{
    static Hashtable<String, String> idToName = new Hashtable<>();
    static Hashtable<String, ArrayList<Station>> stations = new Hashtable<>();
    public static void main(String[] args)
    {

        // 역에 대한 정보를 저장하는 부분
        String fileName = args[0];
        File file = new File(fileName);
        boolean informationChanged = false;
        try {
            Scanner scanner = new Scanner(file, "UTF-8");
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if(line.equals("")) {
                    if(informationChanged) {
                        break;
                    }
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

        // 쿼리를 받아서 최소 시간을 출력하는 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        while (true)
        {
            try
            {
                String input = br.readLine();
                if (input.compareTo("QUIT") == 0)
                    break;
                StringTokenizer stringTokenizer = new StringTokenizer(input);
                String beginName = stringTokenizer.nextToken();
                String destinationName = stringTokenizer.nextToken();

                selectPath(beginName, destinationName);
            }
            catch (IOException e)
            {
                System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
            }
        }
    }

    private static void selectPath(String begin, String destination)
    {
        ArrayList<Station> beginStations = stations.get(begin);
        ArrayList<Station> destinationStations = stations.get(destination);

        Path shortestPath = new Path();
        shortestPath.totalWeight = Long.MAX_VALUE;
        for(Station beginS : beginStations) {
            for(Station destinationS : destinationStations) {
                Path tempPath = findPath(beginS, destinationS);;
                if(tempPath.totalWeight< shortestPath.totalWeight) {
//                    System.out.println(tempPath);
                    shortestPath = tempPath;
                }
            }
        }

        printResult(shortestPath);
    }

    private static void printResult(Path shortest) {
        ArrayList<Station> shortestPath = shortest.savedPath;
        String curStation = "";
        String nextStation = "";
        boolean transferCheck = false;

        for(int i=0; i<shortestPath.size()-1; i++) {
            nextStation = shortestPath.get(i+1).name;
            curStation = shortestPath.get(i).name;
            if(curStation.equals(nextStation)) {
                transferCheck = true;
                continue;
            }
            if(transferCheck) {
                System.out.print("["+curStation+"]");
                System.out.print(" ");
                if(i==shortestPath.size()-2) {
                    System.out.print(shortestPath.get(i+1).name);
                }
                transferCheck = false;
            } else if(i==shortestPath.size()-2){
                System.out.print(curStation);
                System.out.print(" ");
                System.out.print(nextStation);
            } else {
                System.out.print(curStation);
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println(shortest.totalWeight);
    }

    private static Path findPath(Station begin, Station destination) {

        Map<Station, Boolean> completedChecker = new HashMap<>();
        Map<Station, Path> stationToPath = new HashMap<>();

        Set<Station> completed = new HashSet<>();
        Set<String> s = stations.keySet();
        for(String name : s) {
            for(Station station : stations.get(name)) {
                completedChecker.put(station, false);
            }
        }

        // 시작 역 Path에 추가하기
        completed.add(begin);
        completedChecker.put(begin, true);

        Path beginPath = new Path();
        beginPath.savedPath.add(begin);
        beginPath.totalWeight = 0L;
        stationToPath.put(begin, beginPath);

        // notCompleted가 빌때까지 한다.
        while(true) {

            // 가진 completed 중에서 가장 가까운 Station을 가져온다
            Pair pair = closestStationPair(completed, completedChecker, stationToPath);
            if(pair.weight == Long.MAX_VALUE) break;
            Station standardStation = pair.begin;
            Station closestStation = pair.destination;
            Long weight = pair.weight;

            // 가장 가까운 역에 대한 Path가 이미 저장되어 있는 경우, Path를 가져와서 새로운 길이 더 가까우면 Path를 바꿔치기한다.
            if(stationToPath.containsKey(closestStation)) {
//                System.out.println("출발역 :" + standardStation + ", 가까운 역 : " + closestStation);
                Path newPath = stationToPath.get(standardStation).clone();
                if(stationToPath.get(closestStation).totalWeight>newPath.totalWeight+weight) {
                    newPath.savedPath.add(closestStation);
                    newPath.totalWeight += weight;
//                    System.out.println(newPath.totalWeight);
                    stationToPath.replace(closestStation, newPath);
                }
            } else // Path가 등록되어 있지 않은 경우 새로 만들어서 넣어준다.
            {
                Path newPath = stationToPath.get(standardStation).clone();
//                System.out.println("출발역 :" + standardStation + ", 가까운 역 : " + closestStation);
                newPath.savedPath.add(closestStation);
                newPath.totalWeight += weight;
//                System.out.println(newPath.totalWeight);
                stationToPath.put(closestStation, newPath);
            }

//            completedChecker.replace(closestStation, true);
            completed.add(closestStation);
//            System.out.println(completed.size());
//            System.out.println(notCompleted.size());
        }

        return stationToPath.get(destination);
    }
    private static Pair closestStationPair(Set<Station> completed, Map<Station, Boolean> completedCheck, Map<Station, Path> stationToPath) {

        Station closestStation = new Station(null, null, null);
        Station beginStation = new Station(null, null, null);

        Long minWeight=Long.MAX_VALUE;

        for(Station s : completed) {
            for(Edge e : s.edges) {
                if(stationToPath.containsKey(e.destination)) {
                    if(stationToPath.get(e.destination).totalWeight > stationToPath.get(e.begin).totalWeight + e.weight) {
                        if(e.weight<minWeight) {
                            beginStation = e.begin;
                            closestStation = e.destination;
                            minWeight = e.weight;
                        }
                    }
                } else {
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