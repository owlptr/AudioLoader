package audioloader.core;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

public class SoundfileInfo extends Structure implements Structure.ByValue{
	public int channels;
	public int samplerate;	
	
	@Override
	protected List getFieldOrder() {
		return Arrays.asList("channels", "samplerate");
	}
	
	@Override
	public String toString(){
		return "Channels = " + channels + "\tSamplerate = " + samplerate;
	}
}
