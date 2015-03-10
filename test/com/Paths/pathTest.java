package com.Paths;

import org.junit.Test;

import java.io.IOException;
import java.util.*;
import static org.junit.Assert.*;

public class pathTest{
    @Test
    public void test_should_give_root_for_banglore_to_singapore() {
        String[] args = {"Bangalore", "Singapore", "src/file"};
        Root root = new Root(args);
        assertEquals("Bangalore->Singapore", root.givePath());
    }
    @Test
    public void test_should_give_root_for_singapore_to_banglore(){
        String[] args = {"Singapore","Bangalore", "src/file"};
        Root root = new Root(args);
        assertEquals("Singapore->Bangalore", root.givePath());
    }
    @Test
    public void test_should_give_root_for_banglore_to_tokyo(){
        String[] args = {"Bangalore","Tokyo", "src/file"};
        Root root = new Root(args);
        assertEquals("Bangalore->Singapore->Seoul->Beijing->Tokyo", root.givePath());
    }
    @Test
    public void test_should_give_root_for_tokyo_to_bangalore(){
        String[] args = {"Tokyo","Bangalore", "src/file"};
        Root root = new Root(args);
        assertEquals("Tokyo->Beijing->Seoul->Singapore->Bangalore", root.givePath());
    }
    @Test
    public void test_should_give_true_for_singapore_to_seoul(){
        String[] args = {"Singapore","Seoul", "src/file"};
        Root root = new Root(args);
        assertEquals("Singapore->Seoul", root.givePath());
    }
    @Test
    public void test_should_give_true_for_singapore_to_dubai(){
        String[] args = {"Singapore","Dubai", "src/file"};
        Root root = new Root(args);
        assertEquals("Singapore->Dubai", root.givePath());
    }
    @Test
    public void test_should_give_false_for_singapore_to_beijing(){
        String[] args = {"Singapore","Beijing", "src/file"};
        Root root = new Root(args);
        assertEquals("Singapore->Seoul->Beijing", root.givePath());
    }
    @Test
    public void test_should_give_noCity_for_singapore_to_lucknow(){
        String[] args = {"Singapore","Lucknow", "src/file"};
        Root root = new Root(args);
        assertEquals("no city Lucknow", root.givePath());
    }
    @Test
    public void test_should_give_noCity_for_Lucknow_to_Bangalore(){
        String[] args = {"Lucknow","Singapore", "src/file"};
        Root root = new Root(args);
        assertEquals("no city Lucknow", root.givePath());
    }
    @Test
    public void test_should_give_bad_file_name_when_the_file_is_not_exist(){
        String[] args = {"Bangalore", "Singapore", "src/fileeeee"};
        Root root = new Root(args);
        assertEquals("No database named src/fileeeee found.",root.givePath());
    }
}