import java.util.ArrayList;
import java.util.HashMap;

public class StatsObject extends Object {
    ArrayList<String> statsObject = new ArrayList<>();
    String default_alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789,.() '\"![]/%_;?-=:\n";


    //constructor if you know what you want statsObject to contain
    public StatsObject(String str) {
        for (int i = 0; i < str.length(); i++) {
            statsObject.add(str.substring(i,i+1));
        }
    }

    //constructor if you want the statsObject to start with nothing
    public StatsObject() {
    }

    //add a whole string to the end of the statsObject
    public void append(String str) {
        for (int i = 0; i < str.length(); i++) {
            statsObject.add(str.substring(i,i+1));
        }
    }

    //add a value to the end of the statsObject
    public void add(String str) {
        statsObject.add(str);
    }

    public String get(int i) {
        return statsObject.get(i);
    }

    //returns size of statsObject
    public int size() {
        return statsObject.size();
    }

    //print statsObject as a string
    public String toString() {
        String output = "";
        for (int i = 0; i < statsObject.size(); i++) {
            output += statsObject.get(i);
        }
        return output;
    }

    //returns the number of times something was added
    public int getTotalCount() {
        return statsObject.size();
    }

    //Returns list of unique values in the list
    public ArrayList<String> getUniqueValues() {
        ArrayList<String> uniqueCharacters = new ArrayList<>();
        for (int i = 0; i < statsObject.size(); i++) {
            if (!uniqueCharacters.contains(statsObject.get(i))) {
                uniqueCharacters.add(statsObject.get(i));
            }
        }
        return uniqueCharacters;
    }

    //Returns the COUNT of unique values in a list
    public int uniqueValuesCount() { //number of unique values;
        return getUniqueValues().size();
    }

    //returns the count of a specified element CLASS METHOD
    public int getCountOf(String str) {
        int count = 0;
        for (int i = 0; i < statsObject.size(); i++) {
            if (statsObject.get(i).equals(str)) {
                count++;
            }
        }
        return count;
    }

    //count method, not specific to the statsObject
    public int count(ArrayList statsObject, String str) {
        int count = 0;
        for (int i = 0; i < statsObject.size(); i++) {
            if (statsObject.subList(i, i + 1).toString().equals(str)) {
                count++;
            }
        }
        return count;
    }

    //returns the most frequent element of the statsobject
    public String getMostFrequent() {
        ArrayList<String> unique = getUniqueValues();
        int[] frequencies = new int[unique.size()];

        for (int i = 0; i < statsObject.size(); i++) {
            frequencies[unique.indexOf(statsObject.get(i))]++;
        }
        return unique.get(max(frequencies));
    }

    //Get the three most frequent elements of the statsobject.
    public ArrayList<String> get3MostFrequent() {
        ArrayList<String> copy = statsObject;
        ArrayList<String> top3Freq = new ArrayList<>();

        if (copy.size() < 3) {
            return copy;
        }

        for (int i = 0; i < 3; i++) {
            top3Freq.add(GetMostFrequent(copy));
            copy = remove(copy, GetMostFrequent(copy));
        }
        return getUniqueValues(top3Freq);
    }

    //Returns the N most frequent elements of the stats object
    public ArrayList<String> getNMostFrequent(int n) {
        ArrayList<String> copy = statsObject;
        ArrayList<String> topNMostFreq = new ArrayList<>();

        if (copy.size() < n) {
            return copy;
        }

        for (int i = 0; i < n; i++) {
            topNMostFreq.add(GetMostFrequent(copy));
            copy = remove(copy, GetMostFrequent(copy));
        }
        return getUniqueValues(topNMostFreq);
    }

    //returns most frequent for an arraylist
    public String GetMostFrequent(ArrayList<String> arr) {
        ArrayList<String> unique = getUniqueValues();
        int[] frequencies = new int[unique.size()];

        for (int i = 0; i < arr.size(); i++) {
            frequencies[unique.indexOf(arr.get(i))]++;
        }
        return unique.get(max(frequencies));
    }

    //returns max for an array
    public int max(int[] arr) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    //remove all str occurences from arraylist arr
    public ArrayList<String> remove(ArrayList<String> arr, String str) {
        ArrayList<String> removed = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (!arr.get(i).equals(str)) {
                removed.add(arr.get(i));
            }
        }
        return removed;
    }

    //Get unique values for an arraylist, not statsobject
    //used to remove duplicates from input into top3MostFrequent under length == 3
    public ArrayList<String> getUniqueValues(ArrayList<String> arr) {
        ArrayList<String> unique = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if (!unique.contains(arr.get(i))) {
                unique.add(arr.get(i));
            }
        }
        return unique;
    }
}
