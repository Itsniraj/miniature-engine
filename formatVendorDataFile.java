import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.Date;


public class formatVendorDataFile {
    public static void main(String[] args) {
        String[] header = {"START-OF-FILE","PROGRAMNAME=getdata","DATEFORMAT=yyyymmdd","START-OF-FIELDS","Request Identifier","Request Context","END-OF-FIELDS","START-OF-DATA","END-OF-DATA","DATARECORDS=1","END-OF-FILE"};

        SimpleDateFormat formatDate = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        formatDate.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date currentDate = new Date();
        long updatedTimeInMillis = currentDate.getTime() + (2 * 60 * 1000);
        Date updatedTime = new Date(updatedTimeInMillis);
        String formattedDate1 = formatDate.format(currentDate);
        String formattedDate2 = formatDate.format(updatedTime);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path: ");
        String filepath = scanner.nextLine();


        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();

            String[] splittedString = line1.split("\\|");

            FileWriter fw = new FileWriter("D:\\my_repo\\output.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i <= 3; i++){
                bw.write(String.valueOf(header[i]));
                bw.newLine();

            }

            for(int i = 3; i < splittedString.length; i++){
                bw.write(String.valueOf(splittedString[i]));
                bw.newLine();

            }

            for(int i = 4; i <= 6; i++) {
                bw.write(String.valueOf(header[i]));
                bw.newLine();
            }

            bw.write("TIMESTARTED="+formattedDate1);
            bw.newLine();
            bw.write(String.valueOf(header[7]));
            bw.newLine();

            bw.write(line2);
            bw.newLine();

            for(int i = 8; i <= 9; i++) {
                bw.write(String.valueOf(header[i]));
                bw.newLine();
            }

            bw.write("TIMEFINISHED="+formattedDate2);
            bw.newLine();
            bw.write(String.valueOf(header[10]));

            bw.close();
//            D:\my_repo\a.txt



			/* System.out.println("First line: " + line1);
			System.out.println("Second line: " + line2); */

        } catch (IOException e) {
            e.printStackTrace();
        }

		/* try {
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fileWriter);

			System.out.println("Hey! In second try block");

			bw.write(line1);
			bw.newLine();
			bw.write(columnName);

			bw.close();
		} catch(IOException ex) {
			System.out.println("Error writing to file '"+ filename +"'");
		} */
    }
/* 	public static String transpose(String[] substring) {
	StringBuilder sb = new StringBuilder();
	for(String newSubstring : substring) {
		sb.append(newSubstring);
		sb.append("\n");
	}
	return sb.toString();
	} */
}