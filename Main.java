/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;
public class Main
{
    public static LinkedHashMap<String, Integer> sortHashMap(HashMap<String, Integer> mp){		//sorting the current hash map
            List<String> mapKeys = new ArrayList<>(mp.keySet());
            List<Integer> mapValues = new ArrayList<>(mp.values());
            Collections.sort(mapValues);
            Collections.sort(mapKeys);
        
            LinkedHashMap<String, Integer> sortedMap =
                new LinkedHashMap<>();
        
            Iterator<Integer> valueIt = mapValues.iterator();
            while (valueIt.hasNext()) {
                int val = valueIt.next();
                Iterator<String> keyIt = mapKeys.iterator();
        
                while (keyIt.hasNext()) {
                    String key = keyIt.next();
                    int comp1 = mp.get(key);
                    int comp2 = val;
        
                    if (comp1 == comp2) {
                        keyIt.remove();
                        sortedMap.put(key, val);
                        break;
                    }
                }
            }
            return sortedMap;
    }
    public static void print_result(LinkedHashMap<String, Integer> sortedMap, int[] final_arr, int k) throws IOException { 	//function to write result to output.txt file
                FileWriter fw=new FileWriter("C:\\Users\\kusha\\Desktop\\Output.txt");
                fw.write("OUTPUT : \n \n");
                int i = 0;
		        for(Map.Entry<String, Integer> map : sortedMap.entrySet()){
		            //System.out.println(map.getValue());
		            if(i<k && final_arr[i] == map.getValue()){
		                fw.write(map.getKey() + ":" + map.getValue() + "\n");
		                i++;
		            }
		        }
		        fw.close();
    }
    public static int[] find_res_array(int[] arr, int k){					//find array with minimum difference for first and last index
                int start_index = 0, last_index = 0, min = Integer.MAX_VALUE;
		        for(int j=0; j<=arr.length-k; j++){
		               int diff = Math.min(min, arr[k+j-1] - arr[j]);			//maintain the min difference att current index and last index.
		               if(diff<min){
		                   min = diff;
		                   start_index = j;
		                   last_index = k+j-1;
		               }
		        }
		        int i = 0;
		        int[] final_arr = new int[k];
		        for(int s=start_index; s<=last_index; s++){
		            final_arr[i] = arr[s];
		            i++;
		        }
		        return final_arr;
    }
	public static void main(String[] args) throws IOException { 			//main function for taking text from input.txt file and writing output to output.txt file
		FileReader fr = new FileReader("input.txt");
		Scanner sc = new Scanner(fr);
		//String line = sc.nextLine();
		HashMap<String, Integer> mp = new HashMap<>();
		int k = 0;
		while(sc.hasNext()){							//for reading the lines inside the text file
		    String line = sc.nextLine();
		    //String imp_line = line.replace(" ", "");
		    if(line.isEmpty()){							//If there is any empty line then skip that line 
		        continue;
		    }
		    if(line.equals("Goodies and Prices:")){				//if there is goodies and prices line then look into all goodies and prices below it
		        sc.nextLine();
		        while(sc.hasNext()){
		            String new_Line = sc.nextLine();
		            String[] arr = new_Line.split(":");
		            mp.put(arr[0], Integer.parseInt(arr[1].replace(" ", "")));		//hashmap to add string and integer at one place.
		        }
		        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
		        sortedMap = sortHashMap(mp);						//function call to sort the hash map
		        int[] arr = new int[mp.size()];
		        int i = 0;
		        for(Map.Entry<String, Integer> p : mp.entrySet()){			//loop to get only values from the hashmap
		            arr[i] = p.getValue();
		            i++;
		        }
		        Arrays.sort(arr);
		        int[] final_arr = new int[k];
		        final_arr = find_res_array(arr,k);					//function call to find the appropiate array
		        print_result(sortedMap, final_arr, k);					//function call to write result into output.txt file
		    }else{
		        String[] input_line_employee = line.split(":");				//if line is not equal to goodies and prices then take as number of employees
		        k = Integer.parseInt(input_line_employee[1].replace(" ", ""));
		}
	
		}
	}
}
