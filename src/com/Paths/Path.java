package com.Paths;

public class Path{
    public static void main(String[] args)throws Exception {
        String fileName = "file";
        if(args.length > 2)
            fileName = args[2];
        String[] arguments = {args[0],args[1],"file"};
        Root root = new Root(arguments);
        try{
            System.out.println(root.givePath());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}