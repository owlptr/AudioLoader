package audioloader.test;

import java.io.FileNotFoundException;

import audioloader.core.AudioLoader;
import audioloader.core.SoundfileInfo;

public class Tester {
	public static void main(String[] args) {
		AudioLoader audioLoader = null;
		try {
			audioLoader = new AudioLoader("/home/gapeev/Work/Projects/Flac_reader_module/20200203-131000.flac");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		SoundfileInfo soundFileInfo = null;
		
		int readSamples = 0;
		do {
			readSamples = audioLoader.readBlock().length;
			System.out.println("Samples count: " + readSamples);
		}while(readSamples > 0);
		
		soundFileInfo = audioLoader.getSoundfileInfo();
		
		System.out.println(soundFileInfo);
		
		audioLoader.closeAudioStream();
	}
}
