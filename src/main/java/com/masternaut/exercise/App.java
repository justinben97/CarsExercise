package com.masternaut.exercise;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class App 
{
	public static void main( String[] args )
    {
         //Part 1   
		 ArrayList<String> carNames = putCarNamesInAlphabeticalOrder("C:\\Users\\benny\\Documents\\car_data\\car_data.json");
		 System.out.println(carNames);
		 
		 //Part 2
		 writeCarCountJSON(carNames);
		 
		 //Part 3
		 changeAssetTypeIfJumpyOccurs();
		 
    }
	 
	public static void changeAssetTypeIfJumpyOccurs() 
	{
		JSONParser jsonParser = new JSONParser();
		 
	        try (FileReader reader = new FileReader("C:\\Users\\benny\\Documents\\car_data\\car_data.json"))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONArray  carJsonArray = (JSONArray) obj;
	            
	            for(int i =0; i < carJsonArray.size(); i++ )
	            {
	            
	            	JSONObject carJsonObject = (JSONObject)  carJsonArray.get(i);
	            
	            	String model = (String) carJsonObject.get("model");
	            	if(model.toLowerCase().contains("jumpy")) 
	            		{
	            		//if model contains jumpy (case insensitive) 
	            		//change assetType to Van
	            		carJsonObject.put("assetType","VAN");
	            		}
	            	
	               
	            }
	            System.out.println(carJsonArray);
	            try (FileWriter file = new FileWriter("C:\\Users\\benny\\Documents\\car_data\\car_data_part3.json")) 
	            {
	            //write to file
	         	   file.write(carJsonArray.toJSONString()); 
	               file.flush();        	   

	            } 
	            catch (IOException e)
	            {
	            	e.printStackTrace();
	            }   
	        	
	        } catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	}

	public static ArrayList<String> putCarNamesInAlphabeticalOrder(String fileLocation) 
	    {
		 JSONParser jsonParser = new JSONParser();
		 ArrayList<String> cars = new ArrayList<String>();
	        try (FileReader reader = new FileReader(fileLocation))
	        {
	            //Read JSON file
	            Object obj = jsonParser.parse(reader);
	 
	            JSONArray carJsonArray = (JSONArray) obj;
	            
	            for(int i =0; i <carJsonArray.size(); i++ )
	            {
	            
	            JSONObject sampleObject = (JSONObject) carJsonArray.get(i);
	            
	            String make = (String) sampleObject.get("make");
	            cars.add(make);
	            cars.sort(String::compareToIgnoreCase);
	            
	            }
            	
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	        return cars;
         
	        
	    }

	public static void writeCarCountJSON(ArrayList<String> cars)
	 {
		 JSONObject carCount = new JSONObject();
		 for(int i =0; i<cars.size(); i++)
  		 {
  			 String carMake = cars.get(i);
  			  			 
  			int occurrences = Collections.frequency(cars, carMake);
  			 carCount.put(carMake,occurrences);  		   			 
  		 }
		 System.out.println(carCount);
		
       try (FileWriter file = new FileWriter("C:\\Users\\benny\\Documents\\car_data\\CarCount.json")) 
       {
       //write to file
    	   file.write(carCount.toJSONString()); 
           file.flush();
    	   
       } catch (IOException e) 
       {
	   
       e.printStackTrace();
       }
	 } 	 
 }
