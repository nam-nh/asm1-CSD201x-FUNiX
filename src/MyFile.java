
public class MyFile {
	private String name;
	private long size;
	private String fullPath;
	
	public MyFile(String name, long size, String fullPath){
		this.name = name;
		this.size = size;
		this.fullPath = fullPath;
	}

	public String getName() {
		return name;
	}

	public long getSize() {
		return size;
	}

	public String getFullPath() {
		return fullPath;
	}
}
