public class Iterator{

    Map<String, Integer> scores = new TreeMap<String, Integer>();
    Iterator<Map.Entry<String, Integer>> itr = scores.entrySet().iterator();

    while(itr.hasNext()){
        Map.Entry<Integer, String> curr = itr.next();
        System.out.println(curr.getKey() + " " + curr.getValue());
    }

    for(Map.Entry<Integer, String> entry = scores.entrySet()){
        System.out.println(entry.getKey() + "" + entry.getValue);
    }
}