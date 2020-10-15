package core;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

public class AudioLoader {
	
	private static final int SUCCESS_OPEN_FILE = 0;
	private SoundfileInfo soundInfo;
	private String fileName;
	private int blockReadSize = 1024;
	
	public AudioLoader(String fileName) throws FileNotFoundException {
		this.fileName = fileName;
		
		if (Libsndfile.conn.open_audio(fileName) != SUCCESS_OPEN_FILE) {throw new FileNotFoundException();}
		
		soundInfo = Libsndfile.conn.get_audio_info();
	}
	public AudioLoader(String fileName, int blockReadSize) {
		this.fileName = fileName;
		this.blockReadSize = blockReadSize;
		
	}
	
	public double[] read() throws FileNotFoundException {
		return readDouble();
	}
	
	public double[] readBlock() {
		Pointer buffer = new Memory(blockReadSize * 8);
		int readBlockSize = 0;
		
		readBlockSize = Libsndfile.conn.read_double(buffer, blockReadSize);
		return buffer.getDoubleArray(0, readBlockSize);				
	}
	
	private double[] readDouble() throws FileNotFoundException {
		ArrayList<Double[]> soundData = new ArrayList<Double[]>();
		if (Libsndfile.conn.open_audio(fileName) != SUCCESS_OPEN_FILE) {throw new FileNotFoundException();}
		
		Pointer buffer = new Memory(blockReadSize * 8);
		int readBlockSize = 0;
		do {
			readBlockSize = Libsndfile.conn.read_double(buffer, blockReadSize);
			double[] dBuffer = buffer.getDoubleArray(0, readBlockSize);
			Double[] dBufferObject = new Double[readBlockSize];
			
			for(int i = 0; i < readBlockSize; i++) {
				dBufferObject[i] = new Double(dBuffer[i]);
			}
			
			soundData.add(dBufferObject);
			
		} while(readBlockSize > 0);
		
		int commonAraySize = 0;
		
		for (Double[] dArray: soundData) {
			commonAraySize += dArray.length;
		}
		
		Libsndfile.conn.close_audio();
		
		System.out.println("Read data: " + commonAraySize);
		
		double[] commonData = new double[commonAraySize];
				
		int offset = 0;
		for(Double[] dArray: soundData) {
			for(Double d: dArray) {
				commonData[offset] = d;
				offset++;
			}
		}
		
		return commonData;
	}
	
	public void closeAudioStream() {
		Libsndfile.conn.close_audio();
	}
	
//	public float[] readFloat(String fileName) {return null;}
//	public int[] readInt(String fileName) {return null;}
//	
//	public double[] readDoubleFrames(String fileName) {return null;}
//	public float[] readFloatFrames(String fileName) {return null;}
//	public int[] readIntFrames(String fileName) {return null;}
	
	public SoundfileInfo getSoundfileInfo() {
		return soundInfo;
	}
}