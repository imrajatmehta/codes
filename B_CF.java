import java.util.*;
import java.io.*;
public class B_CF{
    public static void main(String[] args ) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        //taking input from user
        // Input Format "year-month-day,year-month-day"
                
        StringTokenizer tok=new StringTokenizer(br.readLine().trim(),",");
        //counting totaldates
        int n=tok.countTokens();
        //for storing dkey value like Date:value
        HashMap<String,Double> dates=new HashMap<>();
        // traversing all the dates
        for(int i=0;i<n;i++){
            // new tok will diffrentiate date and value from date
            StringTokenizer newtok=new StringTokenizer(tok.nextToken(),":");
            // date 
            String date=newtok.nextToken();
            // value correspoing to date
            Double dateValue=Double.parseDouble(newtok.nextToken());
            // checking value is already in hashmap of dates(i.e storing date:value)
            if(dates.containsKey(date)){
                dates.put(date, dateValue+dates.get(date));
            }
            else{
                dates.put(date, dateValue);
            }
        }
         //  find the solution
        HashMap<String,Double> Answer=solution(dates);
        

         
       
    }
    static HashMap<String,Double> solution(HashMap<String,Double> dates){
        // Array of Double will represent the sumValues for particular day
        Double[] weakNumber=new Double[7];
        // represents the day Name
        String[] days=new String[]{ "Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        for(String date:dates.keySet()){
            // find the day Number by using Timohoki ALgo
            int dayNumber=findDayNumber(date);
//             System.out.println(date+"  "+dayNumber+"  "+dates.get(date));
            // As algorithm consider sunday as number 0 so to consider sunday as 7
            if(dayNumber==0){
                dayNumber=6;
            }
            else{
                dayNumber--;
            }
            // getting the values of particaular date from hashmap O(1)
            Double dateValue=dates.get(date);
            // if sumValue contain in weakNumbe ris null that means there no input for that particular day
            if(weakNumber[dayNumber]==null){
                weakNumber[dayNumber]=dateValue;
            }
            else{
                weakNumber[dayNumber]+=dateValue;
            }
        }
        // checking if there is no day have value so it will find mean of next and prev day.
        for(int index=1;index<6;index++){
            if(weakNumber[index]==null){
                int nextIndex=index+1;
                for( nextIndex=index+1;nextIndex<7;nextIndex++){
                    if(weakNumber[nextIndex]!=null){
                        break;
                    }
                }
                int gap=nextIndex-index;
                // put empty days value by finding mean and finding all empty days value;
                fillEmptyDays(gap,weakNumber,index,nextIndex);
                
            }
            
        }
        HashMap<String,Double> answerOfDaysValue=new HashMap<>();
        // return the Days value
        for(int i=0;i<7;i++){
            answerOfDaysValue.put(days[i],weakNumber[i]);
//             System.out.println(days[i]+" " +weakNumber[i]);
        }
        return answerOfDaysValue;
        
    }
    static int findDayNumber(String date){
        // Saperating the date into day,month,year
        StringTokenizer tok=new StringTokenizer(date,"-");
        int year=Integer.parseInt(tok.nextToken());
        int month=Integer.parseInt(tok.nextToken());
        int day=Integer.parseInt(tok.nextToken());
        // Algorithm Timohoki
        int extraDays[] = new int[]{ 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 }; 
        if(month<3)
            year -=1;
        int dayWillbe = ( year + year/4 - year/100 + year/400 + extraDays[month-1] + day) % 7;
        return dayWillbe;
    }
    static void fillEmptyDays(int gap,Double[] weakNumber,int index,int nextIndex){
        // Algorithm to fill Empty days Value;
        // gap represent the how much continously days dont have value
        // other are math Stuff basic
        
        if(gap==1){
            weakNumber[index]=(weakNumber[index-1]+weakNumber[index+1])/2;
        }
        else if(gap==2){
            double a=weakNumber[index-1];
            double d=weakNumber[index+2];
            weakNumber[index]=(2*a+d)/3;
            weakNumber[index+1]=(weakNumber[index]+d)/2;
        }
        else if(gap==3){
            double a=weakNumber[index-1];
            double e=weakNumber[index+3];
            double d=(a+3*e)/4;
            double c=2*d-e;
            double b=2*c-d;
            weakNumber[index]=b;
            weakNumber[index+1]=c;
            weakNumber[index+2]=d;
        }
        else if(gap==4){
            double a=weakNumber[index-1];
            double f=weakNumber[index+4];
            double e=(a+4*f)/5;
            double d=2*e-f;
            double c=2*d-e;
            double b=2*c-d;
            weakNumber[index]=b;
            weakNumber[index+1]=c;
            weakNumber[index+2]=d;
            weakNumber[index+3]=e;
            
        }
        else{
            
            double a=weakNumber[index-1];
            double g=weakNumber[index+5];
            double f=(a+5*g)/6;
            double e=2*f-g;
            double d=2*e-f;
            double c=2*d-e;
            double b=2*c-d;
            weakNumber[index]=b;
            weakNumber[index+1]=c;
            weakNumber[index+2]=d;
            weakNumber[index+3]=e;
            weakNumber[index+4]=f;
        }
        
    }

     
}
