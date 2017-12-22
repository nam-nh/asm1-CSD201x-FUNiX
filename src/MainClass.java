import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class MainClass {
	public static ArrayList<MyFile> myFiles = new ArrayList<MyFile>();
	
	public static void main(String[] args) {
		String path;
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Diretory: ");
		
		// get the path
		path = sc.nextLine();
		getFiles(path);
		
		try {
			while (true) {
				System.out.println("\n1. Sort file\n2. Find the files that a string appears in\n3. Exit");
				// get the result of user
				int result = sc.nextInt();
				if(result == 1){
					while (true) {
						System.out.println("Arrange files by size:");
						System.out.println("1. Selection sort");
						System.out.println("2. Insertion sort");
						System.out.println("3. Back");
						
						int result2 = sc.nextInt();
						
						if(result2 == 1){
							selectionSort(myFiles);
							break;
						} else if(result2 == 2) {
							insertionSort(myFiles);
							break;
						} else if(result2 == 3) {
							break;
						} else {
							System.out.println("Only 1 or 2 or 3 is accepted answers!");
						}
					}	
				
				} else if(result == 2) {
					System.out.println("Input a string: ");
					sc.nextLine();
					String input = sc.nextLine();
					findFiles(input, myFiles);
				} else if(result == 3) {
					break;
				}else {
					System.out.println("Only 1 or 2 or 3 is accepted answers!");
				}
			}
			
		} catch(InputMismatchException e) {
			System.out.println("Answer must be a number");
		}
		
	}
	
	public static void getFiles(String path){
		File dir = new File(path);
        File[] listFile = dir.listFiles();
        for (int i = 0; i < listFile.length; i++) {
            if (listFile[i].isDirectory()) {
                
            	// recusion if the file is a folder
                getFiles(listFile[i].getPath());
                
            } else if (listFile[i].isFile()) {
                System.out.println(listFile[i].getName());
                
                //stored files in array list
                myFiles.add(new MyFile(listFile[i].getName(), listFile[i].length(), listFile[i].getPath()));
            }
        }
	}
	
	public static void selectionSort(ArrayList<MyFile> files){
		for (int i = 0; i < files.size(); i++){
			for (int j = i + 1; j < files.size(); j++){
				if (files.get(j).getSize() < files.get(i).getSize()){
					// swap possition of j to i
					
					// save files[i] to temporary memory
					MyFile tempFile = files.get(i);
					
					// set files[j] to possition i
					files.set(i, files.get(j));
					
					// set the tempFile - old value of files[i] to possition j
					files.set(j, tempFile);
				}
			}
		}
		
		//print the result
		printSortedFile(files);
		
	}
	
	public static void insertionSort(ArrayList<MyFile> files){
		for(int i = 1; i< files.size(); i++){
			int j = i - 1;
			
			MyFile currentFile = files.get(i);
					
			while(j>0 && files.get(j).getSize() > currentFile.getSize()){
				// while size of file in possition j > currentFile's size, insert currentFile to j position
				// and push file in possition j to possition j + 1
				
				files.set(j+1, files.get(j));
				files.set(j, currentFile);
				
				// continue check previous files
				j = j - 1;
			}
		}
		
		//print the result
		printSortedFile(files);
	}
	
	public static void printSortedFile(ArrayList<MyFile> files){
		System.out.println(String.format("|%-50s|%-15s|", "NAME", "SIZE"));
		
		for (int i = 0; i < files.size(); i++){
			System.out.println(String.format("|%-50s|%-15d|", files.get(i).getName(), files.get(i).getSize()));
		}
		
		System.out.println();
	}
	
	public static void findFiles(String input, ArrayList<MyFile> files){
		System.out.println("\nText Files contain '" + input + "':");
		for(int i = 0; i< files.size(); i++){
			if (files.get(i).getName().contains(".txt")){
				
				// get the file content
				File file = new File(files.get(i).getFullPath());
				try {
					@SuppressWarnings("resource")
					Scanner scanner = new Scanner(file);
					
					//linear search (line by line)
					while(scanner.hasNextLine()){
						String line = scanner.nextLine();
						if(line.contains(input)){
							System.out.println(files.get(i).getName());
							break;
						}
					}
				} catch(FileNotFoundException e){
					System.out.println("File not found: " + files.get(i).getFullPath());
				}
			}
		}
	}

}
