import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Subway
{
    final Long Infinity = Long.MAX_VALUE;
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
                    if(stations.containsKey(id)) {
                        ArrayList<Station> prevStations = stations.get(id);
                        for(Station s : prevStations) {
                            s.edges.add(new Edge(s, station, 5L));
                            station.edges.add(new Edge(station, s, 5L));
                        }
                        stations.get(id).add(station);
                    } else {
                        ArrayList<Station> arr = new ArrayList<>();
                        arr.add(station);
                        stations.put(id,arr);
                    }
                } // 아래는 edge 할당하는 부분
                else {
                    // 정보 저장하기
                    StringTokenizer stringTokenizer = new StringTokenizer(line);
                    String beginId = stringTokenizer.nextToken();
                    String destinationId = stringTokenizer.nextToken();
                    Long time = Long.parseLong(stringTokenizer.nextToken());

                    for(Station b : stations.get(beginId)) {
                        for(Station d : stations.get(destinationId)) {
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
        for(String id : s) {
            for(Station ar : stations.get(id)) {
                System.out.println(ar);
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
    }
}