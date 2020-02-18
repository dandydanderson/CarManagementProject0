package daos;

import java.util.List;

//every dao object will need to be able to load and save at a minimum

public interface Dao {

	public void write(Object object);
	
	public Object read();
	
}
