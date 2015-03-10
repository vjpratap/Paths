package com.Paths;

import java.io.IOException;
import java.util.*;

import static com.Paths.CheckRoots.checkRoots;
import static com.Paths.CheckRoots.oppositePath;

class PathDB{
    private static Map<String, Set<String>> map = new HashMap<String, Set<String>>();

    public static Map<String, Set<String>> putValues(String fileName) throws IOException {
        String fileContent = MyFileReader.readingFile(fileName);
        String[] devideByLines = fileContent.split("\r\n");
        for (String line : devideByLines) {
            String[] sourceDestination = line.split(",");
            if (map.containsKey(sourceDestination[0])) {
                map.get(sourceDestination[0]).add(sourceDestination[1]);
            } else {
                Set<String> list = new HashSet<String>();
                list.add(sourceDestination[1]);
                map.put(sourceDestination[0], list);
            }
        }
        return map;
    }
}

class Root{
    Map<String, Set<String>> db;
//    private String[] args;
    private String source, destination, fileName, error;
    public Root(String[] args) {
//        this.args = args;
        this.source = args[0];
        this.destination = args[1];
        this.fileName = args[2];
        this.error = "";
        readDb();
    }
    public void readDb(){
        try {
            db = PathDB.putValues(fileName);
        } catch (IOException e) {
            error = e.getMessage();
        }
    }

    public boolean checkDestination(String city) {
        for(String s : db.keySet())
            if(db.get(s).contains(city))
                return true;
        return false;
    }

    public String giveFileName(){
        return fileName;
    }

    public boolean checkCities(String city) {
        return (db.containsKey(city) || checkDestination(city));
    }

    public String givePath() {
        if(error.length()!=0)
            return error;
        String[] cities = {source, destination};
        for (String city : cities) {
            if (!checkCities(city))
                return "no city " + city;
        }
        String root = CheckRoots.checkRoots(source, destination, source, db);
        return (!root.equals("false")) ? root : CheckRoots.oppositePath(destination, source, db);
    }

}

class CheckRoots{

    public static String checkRoots(String source, String destination, String path,Map<String, Set<String>> db){
        while(db.get(source) != null){
            if ((db.get(source).contains(destination))){
                return path + "->" + destination;
            }
            for(String newSource : db.get(source)){
                path = path + "->" + newSource;
                return checkRoots(newSource, destination,path,db);
            }
        }
        return "false";
    }

    public static String oppositePath(String newSource, String newDestination,Map<String, Set<String>> db){
        String root = checkRoots(newSource, newDestination, newSource, db);
        String[] rootSplit = root.split("->");
        String oppositeRoot = rootSplit[rootSplit.length - 1];
        for (int i = rootSplit.length - 2;i >= 0;i--) {
            oppositeRoot += "->" + rootSplit[i];
        }
        return oppositeRoot;
    }
}
